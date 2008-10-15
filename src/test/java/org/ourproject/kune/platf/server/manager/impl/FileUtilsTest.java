package org.ourproject.kune.platf.server.manager.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileUtilsTest {

    private static final String SIMPLE_FILE_NAME = "simple file name";
    private static final String EXT = ".someext";

    @Test
    public void dotFileRetSeq1() {
        assertEquals("." + SIMPLE_FILE_NAME + " 1", FileUtils.getNextSequentialFileName("." + SIMPLE_FILE_NAME, true));
    }

    @Test
    public void dotFileRetSeq1IgnoreExt() {
        assertEquals("." + SIMPLE_FILE_NAME + " 1", FileUtils.getNextSequentialFileName("." + SIMPLE_FILE_NAME, false));
    }

    @Test
    public void getExtensionBasic() {
        assertEquals("someext", FileUtils.getFileNameExtension("file.name.with.someext", false));
    }

    @Test
    public void getExtensionWithDot() {
        assertEquals("", FileUtils.getFileNameExtension("file name with no ext.", false));
    }

    @Test
    public void getExtensionWithDotBasic() {
        assertEquals(".someext", FileUtils.getFileNameExtension("file.name.with.someext", true));
    }

    @Test
    public void getExtensionWithDotWithFinalDot() {
        assertEquals("", FileUtils.getFileNameExtension("file name with no ext.", true));
    }

    @Test
    public void getExtensionWithDotWithInitialDot() {
        assertEquals("", FileUtils.getFileNameExtension(".file name with no ext", true));
    }

    @Test
    public void getExtensionWithInitialDot() {
        assertEquals("", FileUtils.getFileNameExtension(".file name with no ext", false));
    }

    @Test
    public void testFileNameWExtensionSimple() {
        assertEquals("test", FileUtils.getFileNameWithoutExtension("test.txt", "txt"));
    }

    @Test
    public void testFileNameWExtensionSimpleWithDot() {
        assertEquals("test", FileUtils.getFileNameWithoutExtension("test.txt", ".txt"));
    }

    @Test
    public void testFileNameWithoutExtension() {
        assertEquals("test", FileUtils.getFileNameWithoutExtension("test", ""));
    }

    @Test
    public void testFileNameWNoExtensionWithDot() {
        assertEquals("test.", FileUtils.getFileNameWithoutExtension("test.", ""));
    }

    @Test
    public void testGetFilenameExtNull() {
        assertEquals("", FileUtils.getFileNameExtension(null, true));
    }

    @Test
    public void testIfSeq101return102() {
        assertEquals(SIMPLE_FILE_NAME + " 102", FileUtils.getNextSequentialFileName(SIMPLE_FILE_NAME + " 101"));
    }

    @Test
    public void testIfSeq1return2() {
        assertEquals(SIMPLE_FILE_NAME + " 2", FileUtils.getNextSequentialFileName(SIMPLE_FILE_NAME + " 1"));
    }

    @Test
    public void testIfSeq1WithExtreturn2WithExt() {
        assertEquals(SIMPLE_FILE_NAME + " 2" + EXT, FileUtils.getNextSequentialFileName(SIMPLE_FILE_NAME + " 1" + EXT,
                true));
    }

    @Test
    public void testNoSeqReturn1() {
        assertEquals(SIMPLE_FILE_NAME + " 1", FileUtils.getNextSequentialFileName(SIMPLE_FILE_NAME));
    }

    @Test
    public void testNoSeqWithExtNotTakedIntoAccountReturn1() {
        assertEquals(SIMPLE_FILE_NAME + EXT + " 1", FileUtils.getNextSequentialFileName(SIMPLE_FILE_NAME + EXT, false));
    }

    @Test
    public void testNoSeqWithExtReturn1WithExt() {
        assertEquals(SIMPLE_FILE_NAME + " 1" + EXT, FileUtils.getNextSequentialFileName(SIMPLE_FILE_NAME + EXT, true));
    }

}