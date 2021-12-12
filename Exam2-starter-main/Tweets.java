// EXAM INSTRUCTIONS:
// All of your code for Task 2 goes in this file.
// Add new method headers and implementations as appropriate to these classes
// Add examples to the ArrayExamples class.
import tester.*;

interface Tweet {
  int lengthOfLongestTweetInThread();
  int timesAuthorPostedInThread(User author);
}
class User {
  String username, displayName;
  User(String username, String displayName) {
    this.username = username;
    this.displayName = displayName;
  }
}
class TextTweet implements Tweet {
  User author;
  String contents;
  int likes;
  TextTweet(User author, String contents, int likes) {
    this.author = author;
    this.contents = contents;
    this.likes = likes;
  }

  public int lengthOfLongestTweetInThread() {
    return contents.length();
  }

  public int timesAuthorPostedInThread(User author) {
    if (this.author == author) {
      return 1;
    }
    return 0;
  }
}
class ReplyTweet implements Tweet {
  User author;
  String contents;
  int likes;
  Tweet replyTo;
  ReplyTweet(User author, String contents, int likes, Tweet replyTo) {
    this.author = author;
    this.contents = contents;
    this.likes = likes;
    this.replyTo = replyTo;
  }

  public int lengthOfLongestTweetInThread() {
    int previousLongest = replyTo.lengthOfLongestTweetInThread();
    if (contents.length() > previousLongest) {
      return contents.length();
    }
    return previousLongest;
  }

  public int timesAuthorPostedInThread(User author) {
    int previousTimes = replyTo.timesAuthorPostedInThread(author);
    if (this.author == author) {
      return previousTimes + 1;
    }
    return previousTimes;
  }
}

class ExamplesTweets {
  User joe = new User("joepolitz", "Joe Gibbs Politz");
  User greg = new User("gregory_miranda", "Greg Miranda");
  Tweet t1 = new TextTweet(this.joe, "Java 17 has a cool feature called records", 77);
  Tweet t2 = new ReplyTweet(this.greg, "Hmm I wonder if we could use it for CSE11", 12, this.t1);
  Tweet t3 = new ReplyTweet(this.joe, "It should reduce boilerplate code a lot for when you're only storing data, like with a point", 12, this.t2);
  Tweet t4 = new ReplyTweet(this.greg, "I'm going to have to look into it", 12, this.t3);

  int t1LongestLength = this.t1.lengthOfLongestTweetInThread();
  int t2LongestLength = this.t2.lengthOfLongestTweetInThread();
  int t3LongestLength = this.t3.lengthOfLongestTweetInThread();
  int t4LongestLength = this.t4.lengthOfLongestTweetInThread();
  //class       method                            reference       return
  //TextTweet    lengthOfLongestTweetInThread()     :4              41
  //ReplyTweet   lengthOfLongestTweetInThread()     :5              41
  //ReplyTweet   lengthOfLongestTweetInThread()     :6              92
  //ReplyTweet   lengthOfLongestTweetInThread()     :7              92
  void testLongestTweetInThread(Tester t) {
    t.checkExpect(t1LongestLength, 41);
    t.checkExpect(t2LongestLength, 41);
    t.checkExpect(t3LongestLength, 92);
    t.checkExpect(t4LongestLength, 92);
  }

  void testAuthorPostedInThread(Tester t) {
    t.checkExpect(this.t1.timesAuthorPostedInThread(joe), 1);
    t.checkExpect(this.t1.timesAuthorPostedInThread(greg), 0);
    t.checkExpect(this.t2.timesAuthorPostedInThread(joe), 1);
    t.checkExpect(this.t2.timesAuthorPostedInThread(greg), 1);
    t.checkExpect(this.t3.timesAuthorPostedInThread(joe), 2);
    t.checkExpect(this.t3.timesAuthorPostedInThread(greg), 1);
    t.checkExpect(this.t4.timesAuthorPostedInThread(joe), 2);
    t.checkExpect(this.t4.timesAuthorPostedInThread(greg), 2);
  }
}
    