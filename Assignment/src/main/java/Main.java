import view.Frame;
import view.Panel;

public class Main {

    // 여기서 의존성 주입을 다 처리해야함
    public static void main(String[] args) {

        Panel panel = new Panel();
        new Frame(panel);
    }
}
