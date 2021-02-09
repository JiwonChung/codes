package model.D2;

import view.D2.D2Frame;

// 공 객체
public class D2Ball {
    // 실제 x, y의 위치
    private int _x;
    private int _y;

    // 리프레시 속도
    private double speed;
    // 기울기
    private double inclination;
    // 볼의 진행 방향 오른쪽: true, 왼쪽 : false
    private boolean directionFlag;
    // y 절편
    private double y_intercept = 0;

    // 가짜 x, y 의 위치
    //안녕하세요 과연 이 브랜치는 무엇일까요?
    private int fake_x;
    private int fake_y;


    public int getFake_x() {
        return fake_x;
    }

    public void setFake_x(int fake_x) {
        this.fake_x = fake_x;
        this._x = D2Frame.WIDTH / 2 - 250 + fake_x;
    }

    public int getFake_y() {
        return fake_y;
    }

    public void setFake_y(int fake_y) {
        this.fake_y = fake_y;
        this._y = D2Frame.HEIGHT / 2 + 250 - fake_y;
    }


    public int get_x() {
        return _x;
    }

    public int get_y() {
        return _y;
    }

    public double getSpeed() {
        return speed;
    }

    /**
     * fps 의 역수를 입력해야하는것 정도는 알겠지?
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getInclination() {
        return inclination;
    }

    public void setInclination(double inclination) {
        this.inclination = inclination;
        y_intercept = fake_y - inclination * fake_x;
    }
    public void setY_intercept(double y_intercept) {
        this.y_intercept = y_intercept;
    }

    public boolean isDirectionFlag() {
        return directionFlag;
    }

    public void setDirectionFlag(boolean directionFlag) {
        this.directionFlag = directionFlag;
    }

    public double getY_intercept() {
        return y_intercept;
    }





    /**
     * directionFlag : if true -> increase(go right), else decrease(go left)
     */
    public void move_x() {
        if (Math.abs(inclination) <= 1.0) {
            if (directionFlag) {
                this.fake_x++;
            } else {
                this.fake_x--;
            }
            fake_y = (int) Math.round(inclination * fake_x + y_intercept);
        } else {
            if (directionFlag) {
                if (inclination > 0) {
                    this.fake_y++;
                } else {
                    this.fake_y--;
                }
            } else {
                if (inclination > 0) {
                    this.fake_y--;
                } else {
                    this.fake_y++;
                }
            }
            fake_x = (int) Math.round((fake_y - y_intercept) / inclination);
        }

        this._x = D2Frame.WIDTH / 2 - 250 + fake_x;
        this._y = D2Frame.HEIGHT / 2 + 250 - fake_y;
    }



}
