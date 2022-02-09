import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class main
{
    public static void main(String[] args) throws IOException {
        File temp;
        try
        {

            temp = File.createTempFile("myTempFile", ".txt");
            System.out.println(temp.getPath());
            boolean exists = temp.exists();

            System.out.println("Temp file exists : " + exists);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        bufferedReader();
        copy();
        createDirectories();
        //createTempDirectories();
        //createLink();
        //createSymbolicLink();
        //createTemp
        //delete();
        setattributes();
        //setOwner();
        getattributes();
        getLastModifiedTime();
        //GetPosixFilePermissions();
        //find();
        //probeContentType();
        move();
        //newByteChannel();
        //newDirectoryStream();
        //newInputStream();
        //newOutputStream();
        ReadAllBytes();
        readAllLines();

        walkFileTree();
        //write();
    }
    public static void  bufferedReader() throws IOException {
        Path path = Files.write(Files.createTempFile("test", ".txt"),
                "test content".getBytes());
        System.out.println("Using newBufferedReader for path: " + path);
        System.out.println("Reading lines: ");
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            bufferedReader.lines().forEach(System.out::println);
        }
    }
    //Copy - copying a payload to
    public static  void copy (){
        Path sourceFile = Paths.get("C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\exploit.txt");
        Path targetFile = Paths.get("C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\exploited.txt");

        try {

            Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            System.err.format("I/O Error when copying file");
        }

    }
    //createDirectories - Illegal char <<> at index 0: <image src =q onerror=prompt(8)>
    public static void createDirectories() throws IOException {
        Path tempPath = Files.createTempDirectory("Filestmp");
        Path dirToCreate = tempPath.resolve("eval(location.hash.slice(1))").resolve("test2");
        //creating directories
        Path directories = Files.createDirectories(dirToCreate);
        System.out.println("directories created: " + directories);
        System.out.println("dir created exits: " + Files.exists(directories));
    }
    //createTempDirectories -Illegal char <<> at index 0: <image src =q onerror=prompt(8)>
    public static void createTempDirectories() throws IOException {
        Path tempPath = Files.createTempDirectory("<image src =q onerror=prompt(8)>");
        Path dirToCreate = tempPath.resolve("test1").resolve("test2");
        System.out.println("dir to create: " + dirToCreate);
        System.out.println("dir exits: " + Files.exists(dirToCreate));
        //creating directories
        Path directories = Files.createDirectories(dirToCreate);
        System.out.println("directories created: " + directories);
        System.out.println("dir created exits: " + Files.exists(directories));
    }
    public static void createLink() throws IOException {
        Path newLink = Paths.get("C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\createlink");
        Path target = Paths.get("C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\exploited.txt");
        try {
            Path link = Files.createLink(newLink, target);

        } catch (IOException x) {
            System.err.println(x);
        } catch (UnsupportedOperationException x) {
            // Some file systems do not support symbolic links.
            System.err.println(x);
        }
    }
    //needs root previleges
    //https://stackoverflow.com/questions/9667170/ioexception-a-required-privilege-is-not-held-by-client-while-writing-in-file-i/9667350
    // A required privilege is not held by the client.
    public static void createSymbolicLink() throws IOException {
        Path newLink = Paths.get("C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\");
        Path target = Paths.get("C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\exploited.txt");
        try {
            Files.createSymbolicLink(newLink, target);
        } catch (IOException x) {
            System.err.println(x);
        } catch (UnsupportedOperationException x) {
            // Some file systems do not support symbolic links.
            System.err.println(x);
        }
    }
    public static void delete() throws IOException {
        try
        {
            //file to be delete
            File f= new File("C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\exploited.txt");
            if(f.delete())                      //returns Boolean value
            {
                System.out.println(f.getName() + " deleted");   //getting and printing the file name
            }
            else
            {
                System.out.println("failed");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void find() throws IOException {
        Path path = Paths.get("C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\");

        List<Path> result;
        try (Stream<Path> pathStream = Files.find(path,
                Integer.MAX_VALUE,
                (p, basicFileAttributes) ->
                        p.getFileName().toString().equalsIgnoreCase("exploit.txt"))
        ) {
            result = pathStream.collect(Collectors.toList());
            System.out.println("find:"+ result);
        }

    }
    private static void printAttributes(DosFileAttributes attr) {
        System.out.println("isArchive()  = " + attr.isArchive());
        System.out.println("isHidden()   = " + attr.isHidden());
        System.out.println("isReadOnly() = " + attr.isReadOnly());
        System.out.println("isSystem()   = " + attr.isSystem());
        System.out.println("----------------------------------------");
    }
    public static void setOwner() throws IOException {
        String path = "C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\exploit.txt";
        Path filepath = Paths.get(path);

        UserPrincipal owner = Files.getOwner(filepath);
        System.out.println("Owner: " + owner);

        FileSystem fileSystem = filepath.getFileSystem();
        UserPrincipalLookupService service = fileSystem.getUserPrincipalLookupService();
        UserPrincipal userPrincipal = service.lookupPrincipalByName("joe");
        System.out.println("Found UserPrincipal: " + userPrincipal);
        Files.setOwner(filepath, userPrincipal);
        System.out.println("-- owner after --");
        owner = Files.getOwner(filepath);
        System.out.println("Owner: " + owner.getName());

    }
    public static void SetLastModifiedTimeExample() throws IOException {
        Path path = Files.createTempFile("test-file", ".txt");

        System.out.println("-- lastModifiedTime before --");
        FileTime lastModifiedTime = Files.getLastModifiedTime(path);
        System.out.println(lastModifiedTime);

        Instant instant = Instant.now()
                .minusSeconds(10000 * 24 * 60 * 60);
        FileTime fileTime = FileTime.from(instant);
        Files.setLastModifiedTime(path, fileTime);

        System.out.println("-- lastModifiedTime after --");
        lastModifiedTime = Files.getLastModifiedTime(path);
        System.out.println(lastModifiedTime);
    }
    public static void getLastModifiedTime() throws IOException {
        String path = "C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\exploit.txt";

        Path file = Paths.get(path);
        File file1 = new File(path);
        FileTime date = Files.getLastModifiedTime(file);
        System.out.println("getLastModifiedTime"+ date);
    }
    public static void getattributes() throws IOException {


        String path = "C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\exploit.txt";
        Path file = Paths.get(path);
        File file1 = new File(path);
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("File Exists ? = "+ file1.exists());
        System.out.println("File is Directory  ? = "+ file1.isDirectory());
        System.out.println("File size  ? = "+ attr.size());
        System.out.println("creationTime     = " + attr.creationTime());
        System.out.println("lastAccessTime   = " + attr.lastAccessTime());
        System.out.println("lastModifiedTime = " + attr.lastModifiedTime());
        System.out.println("isDirectory      = " + attr.isDirectory());
        System.out.println("isOther          = " + attr.isOther());
        System.out.println("isRegularFile    = " + attr.isRegularFile());
        System.out.println("isSymbolicLink   = " + attr.isSymbolicLink());
        System.out.println("size             = " + attr.size());
        System.out.println("Filekey             = " + attr.fileKey());



    }
    //I can't call it on windows system
    //https://stackoverflow.com/questions/55621900/get-all-permissions-of-a-file
    public static void GetPosixFilePermissions() throws IOException {
        System.out.println("OS: " + System.getProperty("os.name"));
        String path = "C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\exploit.txt";
        File file1 = new File(path);
        Path path_permission = Paths.get(path);

        Set<PosixFilePermission> set = Files.getPosixFilePermissions(path_permission);
        System.out.println("/tmp  : " + PosixFilePermissions.toString(set));
    }
    //valido posso usar
    public static String inputStreamToString(InputStream inputStream) {
        //In Java 9 use inputStream.readAllBytes()
        return new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
    }
    public static void probeContentType() throws IOException {
        String path = "C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\a.alert(1)";
        Path path_permission = Paths.get(path);

        String contentType = Files.probeContentType(path_permission);
        System.out.println(contentType);
    }
    public static void move() throws IOException {
        Path fileToMovePath = Files.createTempFile(Files.createTempDirectory("testDir1"),
                "test-file", ".txt");
        System.out.println("File to move: " + fileToMovePath);
        System.out.println("File to move exits: " + Files.exists(fileToMovePath));

        Path destinationDir = Files.createTempDirectory("testDir2");
        Path path = Files.move(fileToMovePath, destinationDir.resolve(fileToMovePath.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved to: " + path);
        System.out.println("File moved exits: " + Files.exists(path));
        System.out.println("File to move exits: " + Files.exists(fileToMovePath));
    }
    public static void newByteChannel() throws IOException {
        Path path = Files.createTempFile("test-file", ".txt");
        try (SeekableByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ,
                StandardOpenOption.WRITE)) {
            ByteBuffer byteBuffer = ByteBuffer.wrap("test string".getBytes());
            int b = channel.write(byteBuffer);
            System.out.println("bytes written: " + b);
            channel.position(0);
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(b);
            channel.read(byteBuffer2);
            System.out.println(new String(byteBuffer2.array()));
    }
}
    public static void newDirectoryStream() throws IOException {
            String pathString = System.getProperty("java.io.tmpdir");
            Path path = Paths.get(pathString);
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
                Iterator<Path> iterator = ds.iterator();
                int c = 0;
                while (iterator.hasNext() && c < 5) {
                    Path p = iterator.next();
                    System.out.println(p);
                    c++;
                }
            }
        }
    public static void newInputStream() throws IOException {
        Path path = Files.createTempFile("test-file", ".txt");
        Files.write(path, "<image src =q onerror=prompt(8)>\n".getBytes());

        try (InputStream inputStream = Files.newInputStream(path)) {
            String s = inputStreamToString(inputStream);
            System.out.println(s);
        }


    }
    public static void newOutputStream() throws IOException {
        Path path = Files.createTempFile("test-file", ".txt");
        System.out.println("-- writing to file --");
        try (OutputStream outputStream = Files.newOutputStream(path)) {
            outputStream.write("test file content....".getBytes());
        }

        System.out.println("-- reading from file --");
        Files.readAllLines(path)
                .forEach(System.out::println);
    }
    public static void ReadAllBytes() throws IOException {
        Path path = Files.createTempFile("test-file", ".txt");
        //write
        Files.write(path, "<image src =q onerror=prompt(8)>".getBytes());
        //read
        byte[] bytes = Files.readAllBytes(path);
        System.out.println("ReadAllBytes:"+new String(bytes));
    }
    public static void readAllLines() throws IOException {
        Path path = Files.createTempFile("test-file", ".txt");
        //write
        Files.write(path, "line 1\nline 2\n".getBytes());
        //read all lines
        List<String> lines = Files.readAllLines(path);
        lines.forEach(System.out::println);
    }

    public static void setattributes() throws IOException {
        String path = "C:\\Codigo\\Github\\POC\\JAVA\\NioFiles\\src\\main\\java\\Files\\exploit.txt";
        Path file = Paths.get(path);

        // Get current Dos file attributes and print it.
        DosFileAttributes attr = Files.readAttributes(file, DosFileAttributes.class);
        printAttributes(attr);

        // Set a new file attributes.
        Files.setAttribute(file, "dos:archive", false);

        Files.setAttribute(file, "dos:archive", false);
        Files.setAttribute(file, "dos:hidden", false);
        Files.setAttribute(file, "dos:readonly", false);
        Files.setAttribute(file, "dos:system", false);
        //Files.setAttribute(file, "fileKey", "key");
        // Read the newly set file attributes and print it.
        attr = Files.readAttributes(file, DosFileAttributes.class);
        printAttributes(attr);
    }
    public static void walkFileTree() throws IOException {
        String pathString = "C:\\temp";
        Files.walkFileTree(Paths.get(pathString),new HashSet<>(), 2, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println("preVisitDirectory: " + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println("visitFile: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc)
                    throws IOException {
                System.out.println("visitFileFailed: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException {
                System.out.println("postVisitDirectory: " + dir);
                return FileVisitResult.CONTINUE;
            }
        });

    }

    public static void write() throws IOException {

        FileWriter fileWriter = new FileWriter("exploitwrite.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("Some String");
        printWriter.printf("<image src =q onerror=prompt(8)>\n", "iPhone", 1000);
        printWriter.close();
    }
    }