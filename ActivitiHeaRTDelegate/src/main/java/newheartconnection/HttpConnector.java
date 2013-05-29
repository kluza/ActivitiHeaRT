package newheartconnection;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class HttpConnector {
    /** Name of host, where HeaRT server is working */
    public String hostName = null;
    
    /** Debug mode on (1)/off (0) */
    public boolean debugMode = false;
    
    /** ipAddress of host, where HeaRT server is working */
    public InetAddress ipAddress = null;
    
    /** port where HeaRT server is working */
    public int port = 0;
    
    public HttpConnector(boolean debugMode, String hostName, int port) {
        this.debugMode = debugMode;
        this.hostName = hostName;
        this.port = port;
    }
    
    public HttpConnector(boolean debugMode, InetAddress ipAddress, int port) {
        this.debugMode = debugMode;
        this.ipAddress = ipAddress;
        this.port = port;
    }
    
    public String performRequest(String request) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        Socket socket = null;
        String replay = null;
        
        try {
            
            if ((hostName == null) && (ipAddress != null)) {
                socket = new Socket(ipAddress, port);
            } else if ((ipAddress == null) && (hostName != null)) {
                socket = new Socket(hostName, port);
            }
            
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println(request);
            out.flush();
            
            replay = in.readLine();
        } catch (Exception e) {
            if (debugMode) {
                FileWriter outFile = new FileWriter("debug.log");
                PrintWriter fileWriter = new PrintWriter(outFile);
                
                fileWriter.println(e.getMessage());
                e.printStackTrace(fileWriter);
                fileWriter.close();
                outFile.close();
                
                System.err.println("Error data saved to debug.log");
                throw e;
            } else {
                throw e;
            }
        } finally {
            return replay;
        }
    }
}
