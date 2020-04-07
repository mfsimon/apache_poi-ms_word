package com.lmig.hellomaven;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

//enum row {
//    Date("Date:"),
//}

public class Hello {

    public static void main(String[] args) throws IOException {
        httpConnect();
        openDocx();
//        sqlQuery();
//        printDocx();
    }


    private static void openDocx() throws IOException {
//        File input = new File("input.docx");
//        try (FileInputStream is = new FileInputStream(input.getAbsolutePath())) {
        try (FileInputStream is = new FileInputStream("input.docx")) {
            XWPFDocument docx = new XWPFDocument(is);

//            XWPFParagraph p1 = docx.createParagraph();
//            XWPFRun r1 = new XWPFRun();

            List<XWPFParagraph> paragraphs = docx.getParagraphs();

            for (XWPFParagraph paragraph : paragraphs) {
                for (XWPFRun r1 : paragraph.getRuns()) {
                    r1.setText("[]");
//                    r1.setText("test1",1);
//                    System.out.println(r1.getText(0));
//                    System.out.print(paragraph.getText());

                    if (r1.getText(0).contains("Date:")) {
                        r1.setText(" 4/5/2020");
                    } else if (r1.getText(0).contains("Employee Name:")) {
                        r1.setText(" Daniel Mitola");
                    } else if (r1.getText(0).contains("Date of Loss:")) {
                        r1.setText(" 12/09/2019");
                    } else if (r1.getText(0).contains("Current Employment Status:")) {
                        r1.setText(" Employed");
                    } else if (r1.getText(0).contains("Average Wage:")) {
                        r1.setText(" $50/hr");
                    } else if (r1.getText(0).contains("Litigated?:")) {
                        if (true) {
                            r1.setText(" YES");
                        } else {
                            r1.setText(" NO");
                        }
                    } else if (r1.getText(0).contains("Claim #:")) {
                        r1.setText(" 08D7304ACCFEA6F5");
                    } else if (r1.getText(0).contains("Current Age:")) {
                        r1.setText(" 27");
                    } else if (r1.getText(0).contains("Date of Hire:")) {
                        r1.setText(" 8/12/2019");
                    } else if (r1.getText(0).contains("Compensation Rate:")) {
                        r1.setText(" hourly");
                    } else if (r1.getText(0).contains("Defense Counsel:")) {
                        r1.setText(" Harvey Smith");
                    }
                }

                //FileOutputStream os = new FileOutputStream("output.docx");
                docx.write(new FileOutputStream("output.docx"));
            }
        }
    }

//    private static void sqlQuery() throws SQLException {
//
//        try (
//                // Step 1: Allocate a database 'Connection' object
//                Connection conn = DriverManager.getConnection(
//                        "jdbc:mysql://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
//                        "example_user", "password");   // For MySQL only
//                // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
//
//                // Step 2: Allocate a 'Statement' object in the Connection
//                Statement stmt = conn.createStatement();
//        ) {
//            // Step 3: Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
//            String strSelect = "select * from todo_list";
//            System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
//
//            ResultSet rset = stmt.executeQuery(strSelect);
//
//            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
//            //  For each row, retrieve the contents of the cells with getXxx(columnName).
//            System.out.println("The records selected are:");
//            int rowCount = 0;
//            while (rset.next()) {   // Move the cursor to the next row, return false if no more row
//                String title = rset.getString("title");
//                double price = rset.getDouble("price");
//                int qty = rset.getInt("qty");
//                System.out.println(title + ", " + price + ", " + qty);
//                ++rowCount;
//
//            }
//            System.out.println("Total number of records = " + rowCount);
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
//    }

    private static void httpConnect() throws IOException {
        final ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080...");
        while (true) {
//            Socket clientSocket = server.accept();
//            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
//            BufferedReader reader = new BufferedReader(isr);
//            String line = reader.readLine();
//            while (!line.isEmpty()) {
//                System.out.println(line);
//                line = reader.readLine();
//            }

            try (Socket socket = server.accept()) {
                Date today = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            }
        }
    }
}


/* NOTES *

           BufferedReader isScan = new BufferedReader(is);

            while(isScan.hasNextLine()) {
                String extractedLine = isScan.nextLine();
                System.out.println(extractedLine);
                if (extractedLine.contains("Claim #:") ) {
                    r1.setText("test");
                }
            }
                 fetch paragraph text
            List<XWPFParagraph> paragraphList = docx.getParagraphs();


            //iterate over paragraphList
            for (XWPFParagraph paragraph : paragraphList) {
                System.out.println(paragraph.getText());
            }

            } catch(FileNotFoundException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }


 */
//    public static void printSQL() throws Exception {
//        String filePath = "input.docx";
//        InputStream inputStream = new FileInputStream(new File(filePath));
//
//        String sql = "INSERT INTO person (photo) values (?)";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setBlob(1, inputStream);
//        statement.executeUpdate();
//    }

//    public static void printDocx() throws Exception {
//        try (XWPFDocument doc = new XWPFDocument()) {
//            //doc.createParagraph();
//
//            XWPFParagraph p1 = doc.createParagraph();
////            p1.setAlignment(ParagraphAlignment.LEFT);
////            p1.setAlignment(ParagraphAlignment.CENTER);
//            p1.setAlignment(ParagraphAlignment.RIGHT);
//
//            XWPFRun r1 = p1.createRun();
//            //Version version1 = new Version();
//            //version1.getVersion(); //no! b/c getVersion() is static
//            r1.setText("This is a test of Apache " + Version.getProduct() + " " +
//                    Version.getVersion());
//
//            r1.setFontFamily("Times");
//            r1.setFontSize(18);
//            r1.setUnderline(UnderlinePatterns.SINGLE);
//            r1.addBreak();
////            r1.addCarriageReturn();
//            r1.setText("(" + Version.getReleaseDate() + ")");
////            r1.setTextPosition(100);
//
////            p1.setWordWrapped(true);
////            p1.setPageBreak(true);
//            //p1.setAlignment(ParagraphAlignment.DISTRIBUTE);
////            p1.setAlignment(ParagraphAlignment.BOTH);
////            p1.setSpacingBetween(15, LineSpacingRule.EXACT);
////            p3.setIndentationFirstLine(600);
//
////            XWPFHyperlinkRun hyperlink = p1.insertNewHyperlinkRun(0, "http://poi.apache.org/");
////            hyperlink.setUnderline(UnderlinePatterns.SINGLE);
////            hyperlink.setColor("0000ff");
////            hyperlink.setText("Apache POI");
//            // try-with-resource auto closes fos
//            // writes stream of bytes, for chars use FileWriter
//            try (FileOutputStream os = new FileOutputStream("output.docx")) {
//                doc.write(os);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}


//    public void testSmartTags() throws IOException {
//        try (XWPFDocument doc = XWPFTestDataSamples.openSampleDocument("smarttag-snippet.docx")) {
//            XWPFParagraph p = doc.getParagraphArray(0);
//            assertContains(p.getText(), "Carnegie Mellon University School of Computer Science");
//            p = doc.getParagraphArray(2);
//            assertContains(p.getText(), "Alice's Adventures");
//        }
//    }
//}







//package com.lmig.hellomaven;
//
//import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class Hello {
//    public static String test = "test.txt";
////
//    public static void main (String[] args) throws Exception {
//        System.out.println("Hello Maven!");
//        docHandler();
//    }
//
//
//
//        public static void docHandler() throws Exception {
//            XWPFDocument document = new XWPFDocument();  // MS Word file generation
//            XWPFParagraph title = document.createParagraph();
//            title.setAlignment(ParagraphAlignment.CENTER);
//            XWPFRun titleRun = title.createRun();
//            titleRun.setText("Apache POI Test");
//            FileOutputStream out = new FileOutputStream(test);
//            document.write(out);
//            out.close();
//            document.close();
//        }
////    }
//
//
//
//
//
//    public String convertTextFileToString(String fileName) {
//        try (Stream<String> stream
//                     = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {
//
//            return stream.collect(Collectors.joining(" "));
//        } catch (IOException | URISyntaxException e) {
//            return null;
//        }
//    }
//}

//TODO
// instantiation