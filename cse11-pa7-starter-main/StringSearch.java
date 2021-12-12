import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;

class FileHelper {
  /*
    Takes a path to a file and returns all of the lines in the file as an
    array of strings, printing an error if it failed.
  */
  static String[] getLines(String path) {
    try {
      return Files.readAllLines(Paths.get(path)).toArray(String[]::new);
    }
    catch(IOException e) {
      System.err.println("Error reading file " + path + ": " + e);
      return new String[]{"Error reading file " + path + ": " + e};
    }
  }
}

class StringSearch {
    static String removeEndCharacters(String s) {
        return s.substring(1, s.length() - 1);
    }

    static Query readQuery(String q) {
        if (q.indexOf("not") == 0){
            return new NotQuery(readQuery(q.substring(4, q.length() - 1)));
        }

        String[] queryParameters = q.split("=");
        String name = queryParameters[0];
        String parameter = queryParameters[1];

        switch(name) {
            case "length":
                return new LengthQuery(Integer.parseInt(parameter));
            case "greater":
                return new GreaterQuery(Integer.parseInt(parameter));
            case "less":
                return new LessQuery(Integer.parseInt(parameter));
            case "contains":
                return new ContainsQuery(removeEndCharacters(parameter));
            case "starts":
                return new StartsQuery(removeEndCharacters(parameter));
            case "ends":
                return new EndsQuery(removeEndCharacters(parameter));
            default:
                throw new IllegalArgumentException("Invalid Query Name: " + name);
        }
    }

    static boolean matchesQueries(ArrayList<Query> queries, String s) {
        for (Query query : queries) {
            if (!query.matches(s)) {
                return false;
            }
        }
        return true;
    }

    static Transform readTransform(String t) {
        String[] transformParameters = t.split("=");
        String name = transformParameters[0];

        switch(name) {
            case "upper":
                return new UpperTransform();
            case "lower":
                return new LowerTransform();
            case "first":
                return new FirstTransform(Integer.parseInt(transformParameters[1]));
            case "last":
                return new LastTransform(Integer.parseInt(transformParameters[1]));
            case "replace":
                String[] twoStrings = transformParameters[1].split(";");
                return new ReplaceTransform(removeEndCharacters(twoStrings[0]), removeEndCharacters(twoStrings[1]));
            default:
                throw new IllegalArgumentException("Invalid Transform Name: " + name);
        }
    }

    static String applyTransforms(ArrayList<Transform> transforms, String s) {
        String output = s;
        for (Transform t : transforms) {
            output = t.transform(output);
        }
        return output;
    }
    public static void main(String[] args) {
        String[] lines = FileHelper.getLines(args[0]);

        ArrayList<Query> queries = new ArrayList<>();
        ArrayList<Transform> transforms = new ArrayList<>();
        
        if (args.length >= 2) {
            String[] queryStrings = args[1].split("&");
            for (String s : queryStrings) {
                Query q = readQuery(s);
                queries.add(q);
            }  
        }
        if (args.length >= 3) {
            String[] transformStrings = args[2].split("&");
            for (String s : transformStrings) {
                Transform t = readTransform(s);
                transforms.add(t);
            }  
        }

        for (String line : lines) {
            if (matchesQueries(queries, line)) {
                System.out.println(applyTransforms(transforms, line));
            }
        }
    }
}

/*
    Boring Query Classes
*/
interface Query {
    boolean matches(String s);
}

class LengthQuery implements Query {
    int number;
    LengthQuery(int number) {
        this.number = number;
    }

    public boolean matches(String s) {
        return s.length() == number;
    }
}

class GreaterQuery implements Query {
    int number;
    GreaterQuery(int number) {
        this.number = number;
    }

    public boolean matches(String s) {
        return s.length() > number;
    }
}

class LessQuery implements Query {
    int number;
    LessQuery(int number) {
        this.number = number;
    }

    public boolean matches(String s) {
        return s.length() < number;
    }
}

class ContainsQuery implements Query {
    String string;
    ContainsQuery(String string) {
        this.string = string;
    }

    public boolean matches(String s) {
        return s.contains(string);
    }
}

class StartsQuery implements Query {
    String string;
    StartsQuery(String string) {
        this.string = string;
    }

    public boolean matches(String s) {
        return s.indexOf(string) == 0;
    }
}

class EndsQuery implements Query {
    String string;
    EndsQuery(String string) {
        this.string = string;
    }

    public boolean matches(String s) {
        return string.equals(s.substring(s.length() - string.length(), s.length()));
    }
}

class NotQuery implements Query {
    Query query;
    NotQuery(Query query) {
        this.query = query;
    }

    public boolean matches(String s) {
        return !query.matches(s);
    }
}

/*
    Boring Transform Classes
*/
interface Transform {
    String transform(String s);
}

class UpperTransform implements Transform {
    public String transform(String s) {
        return s.toUpperCase();
    }
}

class LowerTransform implements Transform {
    public String transform(String s) {
        return s.toLowerCase();
    }
}

class FirstTransform implements Transform {
    int number;
    FirstTransform(int number) {
        this.number = number;
    }
    public String transform(String s) {
        return s.substring(0, Math.min(number, s.length()));
    }
}

class LastTransform implements Transform {
    int number;
    LastTransform(int number) {
        this.number = number;
    }
    public String transform(String s) {
        return s.substring(Math.max(s.length() - number, 0), s.length());
    }
}

class ReplaceTransform implements Transform {
    String oldString;
    String newString;
    ReplaceTransform(String oldString, String newString){
        this.oldString = oldString;
        this.newString = newString;
    }
    public String transform(String s) {
        return s.replace(oldString, newString);
    }
}