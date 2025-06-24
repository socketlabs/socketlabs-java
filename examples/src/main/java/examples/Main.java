package examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.socketLabs.injectionApi.SendResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {

        DisplayTheMenu();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean quit = false;
        do
        {
            System.out.println();
            System.out.println();
            System.out.println("Enter a number (or QUIT to exit):");

            String selection = br.readLine();

            if (selection.equals(""))
                continue;

            quit = selection.toLowerCase().trim().equals("quit");
            if (quit)
                continue;

            String exampleClassName = GetExampleName(selection);
            if(exampleClassName == null)
                continue;

            Class<?>  type = Class.forName(exampleClassName);
            Example obj = (Example) type.getConstructor().newInstance();
            Execute(obj);

        } while (!quit);

    }

    private static void DisplayTheMenu() {
        System.out.println();
        System.out.println("Please select from one of the following code examples:");
        System.out.println("NOTE:  Please update the ExampleConfig.cs file with your credentials");
        System.out.println();
        System.out.println(" Basic Send Examples: ");
        System.out.println("    1:  Basic Send ");
        System.out.println("    2:  Basic Send From Html File ");
        System.out.println("    3:  Basic Send With Api Template ");
        System.out.println("    4:  Basic Send With ASCII Charset ");
        System.out.println("    5:  Basic Send With Attachment ");
        System.out.println("    6:  Basic Send With Custom-Headers ");
        System.out.println("    7:  Basic Send With Embedded Image ");
        System.out.println("    8:  Basic Send With Proxy ");
        System.out.println("    9:  Basic Async Send ");
        System.out.println("   10:  Basic Async With Retry");
        System.out.println("   11:  Basic Send With Retry ");
        System.out.println("   12:  Basic Send Complex Example ");
        System.out.println("   13:  Basic Send With HttpClient ");
        System.out.println("   14:  Basic Send With HttpAsyncClient ");
        System.out.println();
        System.out.println(" Validation Error Handling Examples: ");
        System.out.println("   15:  Basic Send With Invalid Attachment");
        System.out.println("   16:  Basic Send With Invalid From ");
        System.out.println("   17:  Basic Send With Invalid Recipients ");
        System.out.println();
        System.out.println(" Bulk Send Examples: ");
        System.out.println("   18:  Bulk Send ");
        System.out.println("   19:  Bulk Send With MergeData ");
        System.out.println("   20:  Bulk Send With ASCII Charset And Merge Data ");
        System.out.println("   21:  Bulk Send From DataSource With Merge Data ");
        System.out.println("   22:  Bulk Send Complex Example ");
        System.out.println();
        System.out.println(" AMP Html Examples: ");
        System.out.println("   23:  Basic Send With Amp Body Example ");
        System.out.println("   24:  Bulk Send With Amp Body Example ");


        System.out.println();
        System.out.println("-------------------------------------------------------------------------");
    }

    private static String GetExampleName(String selection) {
        int menuItem;
        try {
            menuItem = Integer.parseInt(selection);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid Input (Not an integer)");
            return null;
        }

        switch (menuItem)
        {
            case 1: return "examples.basic.BasicSend";
            case 2: return "examples.basic.BasicSendFromHtmlFile";
            case 3: return "examples.basic.BasicSendWithApiTemplate";
            case 4: return "examples.basic.BasicSendWithASCIICharset";
            case 5: return "examples.basic.BasicSendWithAttachment";
            case 6: return "examples.basic.BasicSendWithCustomHeaders";
            case 7: return "examples.basic.BasicSendWithEmbeddedImage";
            case 8: return "examples.basic.BasicSendWithProxy";
            case 9: return "examples.basic.BasicAsync";
            case 10: return "examples.basic.BasicAsyncWithRetry";
            case 11: return "examples.basic.BasicSendWithRetry";
            case 12: return "examples.basic.BasicSendComplexExample";
            case 13: return "examples.basic.BasicSendWithHttpClient";
            case 14: return "examples.basic.BasicSendWithHttpAsyncClient";
            case 15: return "examples.basic.invalid.BasicSendWithInvalidAttachment";
            case 16: return "examples.basic.invalid.BasicSendWithInvalidFrom";
            case 17: return "examples.basic.invalid.BasicSendWithInvalidRecipients";

            case 18: return "examples.bulk.BulkSend";
            case 19: return "examples.bulk.BulkSendWithMergeData";
            case 20: return "examples.bulk.BulkSendWithASCIICharsetMergeData";
            case 21: return "examples.bulk.BulkSendFromDataSourceWithMerge";
            case 22: return "examples.bulk.BulkSendComplexExample";
            case 23: return "examples.basic.BasicSendWithAmpBodyExample";
            case 24: return "examples.bulk.BulkSendWithAmpBodyExample";
            default:
                System.out.println("Invalid Input (Out of Range)");
                return null;
        }
    }

    private static void Execute(Example example) throws Exception
    {
        try
        {
            SendResponse response = example.RunExample();
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

            System.out.println("Response body : ");
            System.out.println(mapper.writeValueAsString(response));

        }
        catch (Exception e)
        {
            System.err.println();
            throw e;
        }
    }

}