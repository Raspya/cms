package dev.bernouy.cms.feature.website.library;

import com.fasterxml.jackson.databind.JsonNode;
import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.LibraryTDB;
import dev.bernouy.cms.tdb.website.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddVersionLibraryTest extends BaseTest {

    @Test
    public void testAddVersionLibrary() {
        LibraryTDB libraryTDB = new LibraryTDB().build();
        Assertions.assertNotNull(libraryTDB.getId());

        VersionTDB versionTDB = new VersionTDB().build();
        libraryTDB.add(versionTDB.getId());
        Assertions.assertEquals(versionTDB.getId(),libraryTDB.list().get(0).get("id").textValue());
    }

    @Test
    public void testAddInvalidVersionLibrary() {
        LibraryTDB libraryTDB = new LibraryTDB().build();
        Assertions.assertNotNull(libraryTDB.getId());

        VersionTDB versionTDB = new VersionTDB().withTypeVersion("coucou").build();
        libraryTDB.add(versionTDB.getId());
        Assertions.assertNull(libraryTDB.list().get(0));
    }

}
