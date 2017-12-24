package com.cooey.maya;

import java.security.SecureRandom;
import java.util.ArrayList;

public class FixturesData {
    protected static ArrayList<String> avatars = new C09641();
    protected static final ArrayList<String> groupChatImages = new C09652();
    protected static final ArrayList<String> groupChatTitles = new C09674();
    protected static final ArrayList<String> messages = new C09685();
    protected static final ArrayList<String> names = new C09663();
    public static SecureRandom rnd = new SecureRandom();

    static class C09641 extends ArrayList<String> {
        C09641() {
            add("http://i.imgur.com/pv1tBmT.png");
            add("http://i.imgur.com/R3Jm1CL.png");
            add("http://i.imgur.com/ROz4Jgh.png");
            add("http://i.imgur.com/Qn9UesZ.png");
        }
    }

    static class C09652 extends ArrayList<String> {
        C09652() {
            add("http://i.imgur.com/hRShCT3.png");
            add("http://i.imgur.com/zgTUcL3.png");
            add("http://i.imgur.com/mRqh5w1.png");
        }
    }

    static class C09663 extends ArrayList<String> {
        C09663() {
            add("Samuel Reynolds");
            add("Kyle Hardman");
            add("Zoe Milton");
            add("Angel Ogden");
            add("Zoe Milton");
            add("Angelina Mackenzie");
            add("Kyle Oswald");
            add("Abigail Stevenson");
            add("Julia Goldman");
            add("Jordan Gill");
            add("Michelle Macey");
        }
    }

    static class C09674 extends ArrayList<String> {
        C09674() {
            add("Samuel, Michelle");
            add("Jordan, Jordan, Zoe");
            add("Julia, Angel, Kyle, Jordan");
        }
    }

    static class C09685 extends ArrayList<String> {
        C09685() {
            add("Hello!");
            add("Hello! No problem. I can today at 2 pm. And after we can go to the office.");
            add("At first, for some time, I was not able to answer him one word");
            add("At length one of them called out in a clear, polite, smooth dialect, not unlike in sound to the Italian");
            add("By the bye, Bob, said Hopkins");
            add("He made his passenger captain of one, with four of the men; and himself, his mate, and five more, went in the other; and they contrived their business very well, for they came up to the ship about midnight.");
            add("So saying he unbuckled his baldric with the bugle");
            add("Just then her head struck against the roof of the hall: in fact she was now more than nine feet high, and she at once took up the little golden key and hurried off to the garden door.");
        }
    }
}
