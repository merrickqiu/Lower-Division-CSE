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
    static Query readQuery(String s) {
        if (s.indexOf("not") == 0){
            return new NotQuery(readQuery(s.substring(4, s.length() - 1)));
        }

        String[] queryParameters = s.split("=");
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

    public static void main(String[] args) {
        String[] lines = FileHelper.getLines(args[0]);
        ArrayList<Query> queries = new ArrayList<>();

        if (args.length == 2) {
            Query q = readQuery(args[1]);
            queries.add(q);
        }

        for (String line : lines) {
            if (matchesQueries(queries, line)) {
                System.out.println(line);
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