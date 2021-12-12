
class User {
  String name;
  String email;
  int followers;

  User(String name, String email, int followers) { 
    this.name = name;
    this.email = email;
    this.followers = followers;
  }
}

class Tweet {
  User author;
  String content;
  String tweetId;
  int likes;
  Date postedDate;

  Tweet(User author, String content, String tweetId, int likes, Date postedDate) {
    this.author = author;
    this.content = content;
    this.tweetId = tweetId;
    this.likes = likes;
    this.postedDate = postedDate;
  }

  boolean before(Tweet other) {
    return this.postedDate.before(other.postedDate);
  }
}

class Date {
  int year;
  int month;
  int day;
  
  Date(int year, int month, int day) { 
    this.year = year;
    this.month = month;
    this.day = day;
  }

  boolean before(Date other) { //
    if (this.year > other.year) { //
      return false; //
    } else if (this.month > other.month) { //
      return false; //
    } else if (this.day >= other.day) { //
      return false; //
    } else { //
      return true; //
    } //
  }
}

class ExamplesTweets {
  User bob = new User("Bob", "bob@gmail.com", 420); 
  
  Date halloweeneve2019 = new Date(2019, 10, 30);
  Date winterSolstice2019 = new Date(2019, 12, 21);
  Tweet tweet1 = new Tweet(bob, "I am almost spooked, my dudes.", "1357924680", 789, halloweeneve2019);
  Tweet tweet2 = new Tweet(bob, "It is winter, my dudes.", "6666666666", 7893, winterSolstice2019); 
  

  Date christmas2020 = new Date(2020, 12, 25); 
  Date newYearsEve2020 = new Date(2020, 12, 31); 
  Tweet tweet3 = new Tweet(bob, "It is christmas, my dudes.", "1234567890", 69, christmas2020); //
  Tweet tweet4 = new Tweet(bob, "It is almost 2021, my dudes.", "9876543210", 96, newYearsEve2020); //

  boolean tweetExample1 = tweet2.before(tweet1); // false
  boolean tweetExample2 = tweet3.before(tweet4); // true
  boolean tweetExample3 = tweet1.before(tweet1); // false
  boolean tweetExample4 = tweet1.before(tweet4); // true
}