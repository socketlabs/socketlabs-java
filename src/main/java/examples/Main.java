package examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.socketLabs.injectionApi.SendResponse;
import examples.Basic.*;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) throws Exception {

       //SendResponse example = new BasicSendWithAttachment().RunExample();

        DisplayTheMenu();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean quit = false;
        do
        {
            System.out.println();
            System.out.println();
            System.out.println("Enter a number (or QUIT to exit):");

            String selection = br.readLine();

            if (selection == "")
                continue;

            quit = selection.toLowerCase().trim() == "quit";
            if (quit)
                continue;

            String exampleClassName = GetExampleName(selection);
            if(exampleClassName == null)
                continue;

            Class<?>  type = Class.forName(exampleClassName);
            Example obj = (Example) type.getConstructor().newInstance();
            Execute(obj);

        } while (quit == false);

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
        System.out.println("    4:  Basic Send With Ascii Charset ");
        System.out.println("    5:  Basic Send With Attachment ");
        System.out.println("    6:  Basic Send With Custom-Headers ");
        System.out.println("    7:  Basic Send With Embedded Image ");
        System.out.println("    8:  Basic Send With Proxy ");
        System.out.println("    9:  Basic Async Send  ");
        System.out.println("   10:  Basic Send Complex Example (Everything including the Kitchen Sink) ");
        System.out.println();
        System.out.println(" Validation Error Handling Examples: ");
        System.out.println("    11:  Basic Send With Invalid Attachment");
        System.out.println("   12:  Basic Send With Invalid From ");
        System.out.println("   13:  Basic Send With Invalid Recipients ");
        System.out.println();
        System.out.println(" Bulk Send Examples: ");
        System.out.println("   14:  Bulk Send ");
        System.out.println("   15:  Bulk Send With MergeDataJson ");
        System.out.println("   16:  Bulk Send With Ascii Charset And MergeDataJson ");
        System.out.println("   17:  Bulk Send From DataSource With MergeDataJson ");
        System.out.println("   18:  Bulk Send Complex Example (Everything including the Kitchen Sink) ");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------");
    }

    private static String GetExampleName(String selection) {
        Integer menuItem;
        try {
            menuItem = Integer.parseInt((String) selection);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid Input (Not an integer)");
            return null;
        }

        switch (menuItem)
        {
            case 1: return "examples.Basic.BasicSend";
            case 2: return "examples.Basic.BasicSendFromHtmlFile";
            case 3: return "examples.Basic.BasicSendWithApiTemplate";
            case 4: return "examples.Basic.BasicSendWithAsciiCharset";
            case 5: return "examples.Basic.BasicSendWithAttachment";
            case 6: return "examples.Basic.BasicSendWithCustomHeaders";
            case 7: return "examples.Basic.BasicSendWithEmbeddedImage";
            case 8: return "examples.Basic.BasicSendWithProxy";
            case 9: return "examples.Basic.BasicAsync";
            case 10: return "examples.Basic.BasicSendComplexExample";
            case 11: return "examples.Basic.Invalid.BasicSendWithInvalidAttachment";
            case 12: return "examples.Basic.Invalid.BasicSendWithInvalidFrom";
            case 13: return "examples.Basic.Invalid.BasicSendWithInvalidRecipients";
            case 14: return "examples.Bulk.BulkSend";
            case 15: return "examples.Bulk.BulkSendWithMergeData";
            case 16: return "examples.Bulk.BulkSendWithAsciiCharsetMergeData";
            case 17: return "examples.Bulk.BulkSendFromDataSourceWithMerge";
            case 18: return "examples.Bulk.BulkSendComplexExample";
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
            System.out.println(e);
            throw e;
        }
    }

}