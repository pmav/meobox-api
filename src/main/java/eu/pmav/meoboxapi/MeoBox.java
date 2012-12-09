package eu.pmav.meoboxapi;

import eu.pmav.meoboxapi.exceptions.MeoBoxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author pmav
 */
public class MeoBox
{

    public static final int KEY_POWER = 233;

    //--
    public static final int KEY_0 = 48;

    public static final int KEY_1 = 49;

    public static final int KEY_2 = 50;

    public static final int KEY_3 = 51;

    public static final int KEY_4 = 52;

    public static final int KEY_5 = 53;

    public static final int KEY_6 = 54;

    public static final int KEY_7 = 55;

    public static final int KEY_8 = 56;

    public static final int KEY_9 = 57;

    public static final int KEY_SPACE = 32;

    public static final int KEY_ENTER = 13;

    //--
    public static final int KEY_VOLUME_UP = 175;

    public static final int KEY_VOLUME_DOWN = 174;

    public static final int KEY_PROGRAM_UP = 33;

    public static final int KEY_PROGRAM_DOWN = 34;

    //--
    public static final int KEY_UP = 38;

    public static final int KEY_DOWN = 40;

    public static final int KEY_LEFT = 37;

    public static final int KEY_RIGHT = 39;

    public static final int KEY_OK = 13;

    //--
    public static final int KEY_MENU = 36;

    public static final int KEY_BROWSER_BACK = 166;
    
    public static final int KEY_BACK = 8;

    public static final int KEY_PAGEUP = 33;

    public static final int KEY_PAGEDOWN = 34;

    public static final int KEY_DELETE = 46;

    public static final int KEY_GREEN = 141;

    public static final int KEY_BLUE = 143;

    public static final int KEY_RED = 140;

    public static final int KEY_YELLOW = 142;

    //--
    public static final int KEY_A = 97;

    public static final int KEY_B = 98;

    public static final int KEY_C = 99;

    public static final int KEY_D = 100;

    public static final int KEY_E = 101;

    public static final int KEY_F = 102;

    public static final int KEY_G = 103;

    public static final int KEY_H = 104;

    public static final int KEY_I = 105;

    public static final int KEY_J = 106;

    public static final int KEY_K = 107;

    public static final int KEY_L = 108;

    public static final int KEY_M = 109;

    public static final int KEY_N = 110;

    public static final int KEY_O = 111;

    public static final int KEY_P = 112;

    public static final int KEY_Q = 113;

    public static final int KEY_R = 114;

    public static final int KEY_S = 115;

    public static final int KEY_T = 116;

    public static final int KEY_U = 117;

    public static final int KEY_V = 118;

    public static final int KEY_W = 119;

    public static final int KEY_X = 120;

    public static final int KEY_Y = 121;

    public static final int KEY_Z = 122;
    /*
    public static final int KEY_A = 65;
    public static final int KEY_B = 66;
    public static final int KEY_C = 67;
    public static final int KEY_D = 68;
    public static final int KEY_E = 69;
    public static final int KEY_F = 70;
    public static final int KEY_G = 71;
    public static final int KEY_H = 72;
    public static final int KEY_I = 73;
    public static final int KEY_J = 74;
    public static final int KEY_K = 75;
    public static final int KEY_L = 76;
    public static final int KEY_M = 77;
    public static final int KEY_N = 78;
    public static final int KEY_O = 79;
    public static final int KEY_P = 80;
    public static final int KEY_Q = 81;
    public static final int KEY_R = 82;
    public static final int KEY_S = 83;
    public static final int KEY_T = 84;
    public static final int KEY_U = 85;
    public static final int KEY_V = 86;
    public static final int KEY_W = 87;
    public static final int KEY_X = 88;
    public static final int KEY_Y = 89;
    public static final int KEY_Z = 90;
     */

    // --
    private String host = "192.168.1.64";

    private int port = 8082;

    //--
    private Socket socket = null;

    private PrintWriter printWriter = null;

    private BufferedReader bufferedReader = null;

    /* ***************************************************** */
    public MeoBox()
    {
    }

    public MeoBox(String host)
    {
        this.host = host;
    }

    public MeoBox(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    /* ***************************************************** */
    public boolean connect() throws MeoBoxException
    {
        try
        {
            this.socket = new Socket(this.host, this.port);

            this.printWriter = new PrintWriter(this.socket.getOutputStream(), true);

            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            String welcomeMessage = this.bufferedReader.readLine();

            if (welcomeMessage != null && welcomeMessage.equalsIgnoreCase("hello"))
            {
                return true;
            } else
            {
                return false;
            }
        } catch (Exception exception)
        {
            throw new MeoBoxException("", exception);
        }
    }

    public boolean disconnect()
    {
        return true;
    }

    public boolean sendKey(int keyCode) throws MeoBoxException
    {
        if (this.socket == null || !this.socket.isConnected())
        {
            throw new MeoBoxException("Not connected to " + this.host + ":" + this.port + ".");
        }

        try
        {
            this.printWriter.println("key=" + keyCode);
            this.printWriter.flush();

            String responseMessage = this.bufferedReader.readLine();

            if (responseMessage != null && responseMessage.equalsIgnoreCase("ok"))
            {
                return true;
            } else
            {
                return false;
            }
        } catch (IOException iOException)
        {
            throw new MeoBoxException("", iOException);
        }
    }

    /* ***************************************************** */
    private void reset()
    {
        this.socket = null;
        this.printWriter = null;
        this.bufferedReader = null;
    }

}
