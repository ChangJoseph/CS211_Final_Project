package integration;

public class IOManager {
    private InputStreamReader isr;
    private BufferedReader br;

    public IOManager(InputStreamReader isr, BufferedReader br) {
        this.isr = isr;
        this.br = br;
    }

    public Student start() {
        try {
            System.out.println("What is you name: ");
            br.getLine;
        }
        catch (IOException ioe) {
            System.out.println("IO Exception raised!");
        }

    }

    public void close() {
        br.close();
    }

    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String  = br.readline();
            System.out.println(name);
        }
        catch (IOException ioe) {
            System.out.println("IO Exception raised!");
        }
    }
}