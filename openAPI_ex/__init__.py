# 컨트롤러 입니다.
# 전술을 선택하여 run 할 수 있도록 만들도록
from repository.kiwoom import KiwoomRepository
from view.ui import UI


class Main:
    def __init__(self):
        print("메인 클래스 입니다. ")


Main()
UI()
KiwoomRepository()

