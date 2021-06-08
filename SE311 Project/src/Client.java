
import java.util.Scanner;
interface USB{
    void cardInsertion();
    void writeData();
    void readData();
}
interface OS{
    void cardInsertion();
    void writeData();
    void readData();
}

class USBStick extends USBActions implements USB {

    private boolean connector = false ;
    private USBActions usbActions ;

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
class SmartCard implements OS{
    private final SmartCardActions smartCardActions ;

    public SmartCard(SmartCardActions smartCardActions) {
        this.smartCardActions = smartCardActions;
    }

    private boolean connector = false ;
    @Override
    public void cardInsertion() {
        connector = true ;
        System.out.println("Smart Card is connected");
    }

    @Override
    public void writeData() {
        if(connector){
            System.out.println("Data is writing...");
            System.out.println("Your data is wrote on your Smart Card");
            smartCardActions.WriteActions();
        }else {
            System.out.println("Please connect your smart card");
        }
    }
    @Override
    public void readData() {
        if(connector){
            System.out.println("Data is reading...");
            smartCardActions.ReadActions();
        }else {
            System.out.println("Please connect your smart card");
        }
    }
}

class OSToUsbAdapter implements OS{
    private final USB usbstick ;

    public OSToUsbAdapter(USB usbstick) {
        this.usbstick = usbstick ;
    }


    @Override
    public void cardInsertion() {
        usbstick.cardInsertion();
    }
    @Override
    public void writeData() {
        usbstick.writeData();
    }
    @Override
    public void readData() {
        usbstick.readData();
    }
}
abstract class Template{

    public void ReadActions(){
        verifyPIN();
        openFile();
        selectFile();
        readData();
        closeFile();
        decryptData();
    }
    public void WriteActions(){
        openFile();
        verifyPIN();
        selectFile();
        encryptData();
        readData();
        decryptData();
    }
    public abstract void openFile();
    public abstract boolean verifyPIN();
    protected  abstract void selectFile();
    protected  abstract void encryptData();
    protected  abstract void readData();
    protected  abstract void decryptData();
    protected  abstract void closeFile();

}

class SmartCardActions extends Template {
    Scanner input = new Scanner(System.in);
    int PIN = input.nextInt();

    @Override
    public void openFile() {
        hook();
    }

    @Override
    public boolean verifyPIN() {
        if(PIN == 1234){
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
        if(verifyPIN())
            System.out.println("Decrypting...");
        else{
            System.out.println("....");
        }
    }

    @Override
    protected void closeFile() {
        hook();
    }
    protected void hook(){

    }
}

class USBActions extends Template {

    @Override
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
    @Override
    protected void closeFile() {
        System.out.println("File is closed");
    }

    protected void hook(){}

}
class Access{
    public Access(SmartCardActions smartCardActions, SmartCard smartCard, USBActions usbActions, USB usbStick, OS usb) {
        smartCardActions = new SmartCardActions();
        smartCard = new SmartCard(smartCardActions);
        usb = new OSToUsbAdapter(usbStick);
    }
        public void Card(){
        smartCard.cardInsertion();
        smartCard.writeData();
        smartCard.readData();
        }
        public void USBStick(){
        usb.cardInsertion();
        usb.writeData();
        usbStick.readData();
        }

    private SmartCardActions smartCardActions;
    private SmartCard smartCard ;
    private USB usbStick;
    private OS usb ;
}

public class Client {
    static void connectUSB(USB usb){
        usb.cardInsertion();
    }
    static  void connectSmartCard(OS smartcard ){
        smartcard.cardInsertion();
    }

    public static void main(String [] args){



    }
}
