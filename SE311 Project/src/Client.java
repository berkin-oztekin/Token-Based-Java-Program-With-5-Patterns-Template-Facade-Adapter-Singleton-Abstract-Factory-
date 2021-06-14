import java.util.ArrayList;
import java.util.Scanner;

interface Open{
    void openUsbStickToken();
    void openSmartCardToken();
}
interface OS{
    void cardInsertion();
    void writeData();
    void readData();
}

class USBStick implements OS {

    private boolean connector = false ; //connectionun bağlı veya değil olduğunu tespit için
    private USBActions usbActions ;

    public USBStick(USBActions usbActions) {
        this.usbActions = usbActions;
    }

    @Override
    public void cardInsertion() {
        connector = true  ;
        System.out.println("USB Stick is connected");
    }

    @Override
    public void writeData() {
        if(connector){
            System.out.println("Data is writing...");
            System.out.println("Your data is wrote on your USB Card");
            usbActions.WriteActions();
        }else{
            System.out.println("Please connect your USB Stick");
        }
    }

    @Override
    public void readData() {
        if(connector){
            System.out.println("Data is reading...");
            System.out.println("Your data is wrote on your USB Card");
            usbActions.ReadActions();
        }else{
            System.out.println("Please connect your USB Stick");
        }
    }

}
class SmartCard {
    private final SmartCardActions smartCardActions ;

    public SmartCard(SmartCardActions smartCardActions) {
        this.smartCardActions = smartCardActions;
    }

    private boolean connector = false ;

    public void cardInsertion() {
        connector = true ;
        System.out.println("Smart Card is connected");
    }

    public void writeData() {
        if(connector){
            System.out.println("Data is writing...");
            System.out.println("Your data is wrote on your Smart Card");
            smartCardActions.WriteActions();
        }else {
            System.out.println("Please connect your smart card");
        }
    }

    public void readData() {
        if(connector){
            System.out.println("Data is reading...");
            smartCardActions.ReadActions();
        }else {
            System.out.println("Please connect your smart card");
        }
    }
}

class OSToSmartCardAdapter implements OS{
    private final SmartCard smartCard ;

    public OSToSmartCardAdapter(SmartCard smartCard) {
        this.smartCard = smartCard ;
    }

    @Override
    public void cardInsertion() {
        smartCard.cardInsertion();
    }
    @Override
    public void writeData() {
        smartCard.writeData();
    }
    @Override
    public void readData() {
        smartCard.readData();
    }
}

//Template pattern starts below

abstract class Template{

    public void ReadActions(){
       // verifyPIN();
      //  openFile();
        selectFile();
        readData();
        closeFile();
        decryptData();
    }
    public void WriteActions(){
      // openFile();
       // verifyPIN();
        selectFile();
        encryptData();
        readData();
        decryptData();
    }

  //  protected  abstract void openFile();
    protected  abstract boolean verifyPIN();
    protected  abstract void selectFile();
    protected  abstract void encryptData();
    protected  abstract void readData();
    protected  abstract void decryptData();
    protected  abstract void closeFile();

}

class SmartCardActions extends Template {

//    @Override
//    public void openFile() {
//        hook();
//    }

    @Override
    public boolean verifyPIN() {
        if(1234 == 1234){
            System.out.println("Your pin is verified ");
            return true ;
        }else{
            System.out.println("Your pin is rejected from system...");
            return  false ;
        }
    }
    protected void selectFile() {
        if(verifyPIN())
            System.out.println("File is selected...");
        else{
            System.out.println("File couldn't select");
        }
    }
    protected void encryptData() {
        if(verifyPIN() )
            System.out.println("Data is encrypted");
        else {
            System.out.println("Data couldn't select");
        }
    }
    protected void readData() {
       hook();
    }

    protected void decryptData() {
        System.out.println("Decrypting....");
//        if(verifyPIN())
//            System.out.println("Decrypting...");
//        else{
//            System.out.println("....");
//        }
    }

    @Override
    protected void closeFile() {
        hook();
    }

    protected void hook(){
        //Boş dönmesi için
    }
}

class USBActions extends Template {

  //  @Override
    public void openFile() {
        System.out.println("File is opened");
    }
    public boolean verifyPIN(){
        hook();
        return true ;
    }
    protected void selectFile() {
        System.out.println("File is selected...");
    }
    protected void encryptData() {
        System.out.println("Data is encrypted");
    }
    protected void readData() {
       hook();
    }
    protected void decryptData() {
        System.out.println("Decrypting...");
    }
    protected void closeFile() {
        System.out.println("File is closed");
    }

    protected void hook(){}

}
class Access implements Open{

    // Hem usb hem de smart cardın çalıştırıldığı yer

    public Access() {
        usbActions = new USBActions();
        usbStick = new USBStick(usbActions);

        smartCardActions = new SmartCardActions();
        smartCARD = new SmartCard(smartCardActions);
        smartCard = new OSToSmartCardAdapter(smartCARD);
    }
    @Override
    public void openUsbStickToken() {
        usbStick.cardInsertion();
        sockets.add(usbStick);
        usbStick.readData();
        usbStick.writeData();

    }
    @Override
    public void openSmartCardToken() {
        smartCard.cardInsertion();
        sockets.add(smartCard);
        smartCard.readData();
        smartCard.writeData();
    }


   private USBStick usbStick ;
   private USBActions usbActions;
   private SmartCard smartCARD ;
   private OSToSmartCardAdapter smartCard ;
   private SmartCardActions smartCardActions;

   ArrayList <OS> sockets = new ArrayList<OS>(4);
}

class Singleton extends Access{

    private static Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){

        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}


public class Client {
    public static void main(String [] args){
        Singleton singleton = Singleton.getInstance();
        singleton.openSmartCardToken(); //Singletondan aldığımız objeyi çağırıyoruz

//        singleton.openUsbStickToken();
//        Open usbStick = new Access();
//        usbStick.openUsbStickToken();
//
//        System.out.println();
//        System.out.println("***********************************");
//        System.out.println();
//
//        Open smartCard = new Access();
//        smartCard.openSmartCardToken();



    }
}
