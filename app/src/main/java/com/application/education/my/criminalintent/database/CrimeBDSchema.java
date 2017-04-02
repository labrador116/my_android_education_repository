package com.application.education.my.criminalintent.database;

/**
 * Created by magaz on 14.03.2017.
 */

public class CrimeBDSchema {
    public static final class CrimeTable {
        public static final String NAME = "crimes";

        public static final class Columns {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
            public static final String ADDRESS_BOOK_ID = "address_id";
        }
    }
}
