import view.D2.D2Frame;
import view.D2.D2Panel;

public class Main {

    // 여기서 의존성 주입을 다 처리해야함
    public static void main(String[] args) {
        D2Panel d2Panel = new D2Panel();
        new D2Frame(d2Panel);
    }
}
