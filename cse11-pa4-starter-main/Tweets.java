import tester.*;

interface Tweet {
    public boolean isReplyTo(Tweet other);
    public int totalLikes();
    public String allAuthors();
    public boolean textAppearsOnThread(String text);
}

class User {
    String username;
    String displayName;
    int followers;

    // Constructor
    User(String username, String displayName, int followers) {
        this.username = username;
        this.displayName = displayName;
        this.followers = followers;
    }

    // Returns a string with the display and username of the user
    public String toText() {
        return displayName + " @" + username; 
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
    public boolean isReplyTo(Tweet other) {
        return false;
    }

    public int totalLikes() {
        return this.likes;
    }

    public String allAuthors() {
        return this.author.username;
    }

    public boolean textAppearsOnThread(String text) {
        return this.contents.contains(text);
    }
}

class ReplyTweet implements Tweet {
    User author;
    String contents;
    int likes;
    Tweet replyTo;

    ReplyTweet(User author, String contents, int likes,  Tweet replyTo) {
        this.author = author;
        this.contents = contents;
        this.likes = likes;    
        this.replyTo = replyTo;
    }
    public boolean isReplyTo(Tweet other) {
        return other == this.replyTo;
    }

    public int totalLikes() {
        return this.likes + this.replyTo.totalLikes();
    }

    public String allAuthors() {
        return this.author.username + ";" + replyTo.allAuthors();
    }

    public boolean textAppearsOnThread(String text) {
        return this.contents.contains(text) || this.replyTo.textAppearsOnThread(text);
    }
}

class ExamplesTweets {
    User joe = new User("joepolitz", "Joe Gibbs Politz", 999);
    User greg = new User("gregory_miranda", "Greg Miranda", 9999);
    User rachel = new User("Rachel__Lim", "Rachel Lim", 1000000);
    Tweet t1 = new TextTweet(this.joe, "Java 17 has a cool feature called records", 77);
    Tweet t2 = new ReplyTweet(this.greg, "Hmm I wonder if we could use it for CSE11", 12, this.t1);
    Tweet t3 = new ReplyTweet(this.greg, "Thought about this more, probably not yet, too new.", 73, this.t2);
    Tweet t4 = new ReplyTweet(this.joe, "Yeah, good point. Maybe in 2022.", 10, this.t3);
    Tweet t5 = new ReplyTweet(this.rachel, "Yeah... I don't want to rewrite the book right this minute", 1005, this.t2);

    void testIsReplyTo(Tester t) {
        t.checkExpect(this.t1.isReplyTo(this.t2), false);
        t.checkExpect(this.t2.isReplyTo(this.t1), true);
        t.checkExpect(this.t5.isReplyTo(this.t2), true);
        t.checkExpect(this.t2.isReplyTo(this.t2), false);
        t.checkExpect(this.t4.isReplyTo(this.t3), true);
    }

    void testTotalLikes(Tester t) {
        t.checkExpect(this.t5.totalLikes(), 1005 + 12 + 77);
        t.checkExpect(this.t4.totalLikes(), 10 + 73 + 12 + 77);
        t.checkExpect(this.t1.totalLikes(), 77);
    }

    void testAllAuthors(Tester t) {
        t.checkExpect(this.t1.allAuthors(), "joepolitz");
        t.checkExpect(this.t2.allAuthors(), "gregory_miranda;joepolitz");
        t.checkExpect(this.t3.allAuthors(), "gregory_miranda;gregory_miranda;joepolitz");
        t.checkExpect(this.t5.allAuthors(), "Rachel__Lim;gregory_miranda;joepolitz");
    }

    void testTextAppearsOnThread(Tester t) {
        t.checkExpect(this.t1.textAppearsOnThread("joepolitz"), false);
        t.checkExpect(this.t1.textAppearsOnThread("2022"), false);
        t.checkExpect(this.t1.textAppearsOnThread("cool"), true);
        t.checkExpect(this.t4.textAppearsOnThread("wonder"), true);
        t.checkExpect(this.t4.textAppearsOnThread("Java"), true);
        t.checkExpect(this.t4.textAppearsOnThread("rewrite"), false);
        t.checkExpect(this.t4.textAppearsOnThread("2022"), true);
    }

    //My 3 authors
    User alice = new User("alice_smith", "Alice Smith", 666);
    User bob = new User("bob_brown", "Bob Brown", 6666);
    User eve = new User("eve_taylor", "Eve Taylor", 6000000);

    //My 5 tweets
    Tweet m1 = new TextTweet(this.alice, "Hey, when are we going to share our secret key?", 34);
    Tweet m2 = new ReplyTweet(this.bob, "Hmm, we should do it in person.", 50, this.m1);
    Tweet m3 = new ReplyTweet(this.bob, "Let's do it at the same place, same time.", 29, this.m2);
    Tweet m4 = new ReplyTweet(this.alice, "All right, see you then.", 11, this.m3);
    Tweet m5 = new ReplyTweet(this.eve, "Hehe... I already know your secret key.", 421, this.m2);

    //My 2x4 method tests
    void testMyIsReplyTo(Tester t) {
        t.checkExpect(this.m1.isReplyTo(this.m1), false);
        t.checkExpect(this.m5.isReplyTo(this.m2), true);
    }

    void testMyTotalLikes(Tester t) {
        t.checkExpect(this.m5.totalLikes(), 421 + 50 + 34);
        t.checkExpect(this.m1.totalLikes(), 34);
    }

    void testMyAllAuthors(Tester t) {
        t.checkExpect(this.m1.allAuthors(), "alice_smith");
        t.checkExpect(this.m5.allAuthors(), "eve_taylor;bob_brown;alice_smith");
    }

    void testMyTextAppearsOnThread(Tester t) {
        t.checkExpect(this.t1.textAppearsOnThread(""), true);
        t.checkExpect(this.t4.textAppearsOnThread("HEY"), false);
    }
}