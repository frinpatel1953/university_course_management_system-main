// package utils;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.IOException;

// /**
//  * Utility class to check if a file is a binary .dat file.
//  */
// public class BinaryFileCheck {

//   /**
//    * Checks if a file is a binary .dat file.
//    * 
//    * @param fileName the name of the file to check
//    * @return true if the file is binary and has .dat extension, false otherwise
//    * @throws IOException if the file is not found or the format is invalid
//    */
//   public static boolean isBinaryFile(String fileName) throws IOException {
//     File file = new File(fileName);
//     if (!file.exists())
//       return false;

//     // Check if the file extension is ".dat"
//     if (!fileName.endsWith(".dat")) {
//       throw new IOException("Invalid file format. Expected a .dat file.");
//     }

//     // Read the first few bytes to check if it's binary
//     try (FileInputStream fis = new FileInputStream(file)) {
//       byte[] buffer = new byte[10]; // Read first 10 bytes
//       int bytesRead = fis.read(buffer);
//       if (bytesRead == -1)
//         return false;

//       // Check for non-printable characters indicating a binary file
//       for (byte b : buffer) {
//         if (b < 0x09 || (b > 0x0D && b < 0x20) || b > 0x7E) {
//           return true; // Likely a binary file
//         }
//       }
//       return false; // If it contains only readable ASCII, it may not be binary
//     }
//   }
// }
