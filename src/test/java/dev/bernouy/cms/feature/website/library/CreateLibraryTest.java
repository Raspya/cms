package dev.bernouy.cms.feature.website.library;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.LibraryTDB;
import dev.bernouy.cms.tdb.website.ProjectTDB;
import dev.bernouy.cms.tdb.website.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateLibraryTest extends BaseTest {

    @Test
    public void testCreateLibrary() {
        LibraryTDB libraryTDB = new LibraryTDB().build();
        Assertions.assertNotNull(libraryTDB.getId());
    }


}
