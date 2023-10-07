package dev.bernouy.cms.feature.website.library;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.LibraryTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetNameLibraryTest extends BaseTest {

    @Test
    public void testSetNameLibrary() {
        LibraryTDB libraryTDB = new LibraryTDB().build();
        libraryTDB.setName("Library");
        Assertions.assertEquals("Library", libraryTDB.getName());
    }

    @Test
    public void testInvalidSetNameLibrary() {
        LibraryTDB libraryTDB = new LibraryTDB().build();
        libraryTDB.setName("ok");
        Assertions.assertEquals("undefined", libraryTDB.getName());
    }
}
