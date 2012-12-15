package eu.pmav.meoboxapi;

import eu.pmav.meoboxapi.exceptions.MeoBoxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MeoBoxConsole
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            MeoBox meoBox = new MeoBox();
            meoBox.connect();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true)
            {
                System.out.print("> ");
                String keyCode = br.readLine();

                meoBox.sendKey(Integer.parseInt(keyCode));
            }
        }
        catch (MeoBoxException ex)
        {
            Logger.getLogger(MeoBoxConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
