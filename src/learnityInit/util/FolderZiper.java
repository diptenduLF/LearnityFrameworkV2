package learnityInit.util;

import java.util.Enumeration;
import java.util.zip.*;

import comv2.aunwesha.lfutil.FileUtil;

import java.io.*;

public class FolderZiper
{
    private ZipOutputStream zip;
    private FileOutputStream fileWriter;
    
    public FolderZiper() {
        this.zip = null;
        this.fileWriter = null;
    }
    
    public void zipFolder(final String srcFolder, final String destZipFile) {
        try {
            this.fileWriter = new FileOutputStream(destZipFile);
            this.zip = new ZipOutputStream(this.fileWriter);
            this.addFolderToZip("", srcFolder);
        }
        catch (Exception ex) {
            System.out.println("Exception in zipFolder()");
            ex.printStackTrace();
            try {
                this.zip.flush();
                this.zip.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        finally {
            try {
                this.zip.flush();
                this.zip.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    private void addToZip(final String path, final String srcFile) {
        final File folder = new File(srcFile);
        if (folder.isDirectory()) {
            this.addFolderToZip(path, srcFile);
        }
        else {
            final byte[] buf = new byte[1024];
            FileInputStream in = null;
            try {
                in = new FileInputStream(srcFile);
                if (path.equals("")) {
                    this.zip.putNextEntry(new ZipEntry(folder.getName()));
                }
                else {
                    this.zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
                }
                int len;
                while ((len = in.read(buf)) > 0) {
                    this.zip.write(buf, 0, len);
                }
            }
            catch (Exception ex) {
                System.out.println("Exception in addToZip()");
                ex.printStackTrace();
                try {
                    in.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            finally {
                try {
                    in.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    
    private void addFolderToZip(final String path, final String srcFolder) {
        final File folder = new File(srcFolder);
        final String[] fileListe = folder.list();
        try {
            for (int i = 0; i < fileListe.length; ++i) {
                final File fileItem = new File(srcFolder + "/" + fileListe[i]);
                if (fileItem.isFile()) {
                    this.addToZip(path, srcFolder + "/" + fileListe[i]);
                }
                else {
                    String path2 = "";
                    if (path.equals("")) {
                        path2 = fileListe[i];
                    }
                    else {
                        path2 = path + "/" + fileListe[i];
                    }
                    this.addToZip(path2, srcFolder + "/" + fileListe[i]);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Exception in addFolderToZip()");
            ex.printStackTrace();
        }
    }
    
    public void close() throws IOException {
        if (this.zip != null) {
            this.zip.close();
        }
    }
    
    public static void unzip(String dirName, String strLocation) {
    	File dir = new File(strLocation);
    	// create output directory if it doesn't exist
    	if(!dir.exists()) dir.mkdirs();
    	FileInputStream fis;
    	//buffer for read and write data to file
    	byte[] buffer = new byte[1024];
    	try {
    		System.out.println("File Name: "+dirName);
    		fis = new FileInputStream(dirName);
    		ZipInputStream zis = new ZipInputStream(fis);
    		ZipEntry ze = zis.getNextEntry();
    		while(ze != null){
    			String fileName = ze.getName();
    			System.out.println("fileName: "+fileName);
    			File newFile = new File(strLocation + File.separator + fileName);
    			System.out.println("Unzipping to "+newFile.getAbsolutePath());
    			//create directories for sub directories in zip
    			new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                	fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
    		}
    		//close last ZipEntry
    		zis.closeEntry();
    		zis.close();
    		fis.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        
    }
    
    public static void decompressGzip(String dirName, String strLocation) {
    	File dir = new File(strLocation);
    	// create output directory if it doesn't exist
    	if(!dir.exists()) dir.mkdirs();
    	FileInputStream fis;
    	//buffer for read and write data to file
    	byte[] buffer = new byte[1024];
    	try {
    		System.out.println("File Name: "+dirName+", strLocation: "+strLocation);
    		File file = new File(dirName);
    		fis = new FileInputStream(dirName);
    		GZIPInputStream zis = new GZIPInputStream(fis);
    		File target = new File(strLocation, FileUtil.StripFileExtension(file.getName()));
    		//ZipInputStream zis = new ZipInputStream(fis);
    		
    		OutputStream out = new FileOutputStream(target);

            // Transfer bytes from the compressed file to the output file
            byte[] buf = new byte[1024];
            int len;
            while ((len = zis.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            // Close the file and stream
            zis.close();
            out.close();
    		fis.close();
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        
    }
    
    public static String decompressGzip(String dirName, String strLocation ,String zipType) {
    	File dir = new File(strLocation);
    	// create output directory if it doesn't exist
        String filepath="";
    	if(!dir.exists()) dir.mkdirs();
    	FileInputStream fis;
    	//buffer for read and write data to file
    	byte[] buffer = new byte[1024];
    	try {
    		System.out.println("File Name: "+dirName+", strLocation: "+strLocation+", zipType: "+zipType);
    		File file = new File(dirName);
    		fis = new FileInputStream(dirName);
    		
    		InputStream is = null;
    		switch(zipType) {
    		
	    		case "application/x-gzip":
	        		is = new GZIPInputStream(fis);
	        		File target = new File(strLocation, FileUtil.StripFileExtension(file.getName()));
	        		//ZipInputStream zis = new ZipInputStream(fis);
	        		filepath=strLocation+FileUtil.StripFileExtension(file.getName());
	        		OutputStream out = new FileOutputStream(target);

	                // Transfer bytes from the compressed file to the output file
	                byte[] buf = new byte[1024];
	                int lenz;
	                while ((lenz = is.read(buf)) > 0) {
	                    out.write(buf, 0, lenz);
	                }

	                // Close the file and stream
	                is.close();
	                out.close();
	        		fis.close();	
	    			break;
	    			
	    		case "application/x-zip-compressed":
	    			ZipFile zipFile = new ZipFile(dirName);
	    			Enumeration<?> enu = zipFile.entries();
	    			int i=0;
	                while (enu.hasMoreElements()) {
	                	ZipEntry zipEntry = (ZipEntry) enu.nextElement();

	                    String name = zipEntry.getName();
	                    long size = zipEntry.getSize();
	                    long compressedSize = zipEntry.getCompressedSize();
	                    System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", 
	                            name, size, compressedSize);

	                    // Do we need to create a directory ?
	                    File fileZip = new File(strLocation+File.separator+name);
	                    System.out.println("name===="+name);
	                    if (name.endsWith("/")) {
	                    	fileZip.mkdir();
	                    	if(i==0)
	                    		filepath = strLocation+File.separator+name;
	                    	continue;
	                    }
	                    
	                    System.out.println("fileZip.getParentFile()==="+fileZip.getParentFile().toString());

	                    File parent = fileZip.getParentFile();
	                    if (parent != null) {
	                        parent.mkdir();
	                    }
	                    
	                    if(filepath.equals("") && i==0)
	                    	filepath = strLocation+File.separator+name;

	                    // Extract the file
	                    InputStream isZip = zipFile.getInputStream(zipEntry);
	                    FileOutputStream fos = new FileOutputStream(fileZip);
	                    byte[] bytes = new byte[1024];
	                    int length;
	                    while ((length = isZip.read(bytes)) >= 0) {
	                        fos.write(bytes, 0, length);
	                    }
	                    isZip.close();
	                    fos.close();
	                    i++;
	                }
	                zipFile.close();
	        	 
	    			break;

    		}
    		
    		
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		return filepath;
    	
        
    }
}
