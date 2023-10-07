package dev.bernouy.cms.feature.website.library;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.LibraryTDB;
import dev.bernouy.cms.tdb.website.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemoveVersionLibraryTest extends BaseTest {

    @Test
    public void testRemoveVersionLibrary() {
        LibraryTDB libraryTDB = new LibraryTDB().build();
        Assertions.assertNotNull(libraryTDB.getId());

        VersionTDB versionTDB = new VersionTDB().build();
        libraryTDB.remove(versionTDB.getId());
        libraryTDB.add(versionTDB.getId());
        libraryTDB.remove(versionTDB.getId());
        Assertions.assertNull(libraryTDB.list().get(0));
    }

    @Test
    public void testRemoveInvalidVersionLibrary() {
        LibraryTDB libraryTDB = new LibraryTDB().build();
        Assertions.assertNotNull(libraryTDB.getId());

        VersionTDB versionTDB = new VersionTDB().withTypeVersion("coucou").build();
        libraryTDB.remove(versionTDB.getId());
        Assertions.assertNull(libraryTDB.list().get(0));
    }
}
