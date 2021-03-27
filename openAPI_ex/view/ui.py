from PyQt5.QtWidgets import *
import sys


class UI:
    def __init__(self):
        self.app = QApplication(sys.argv)
        print("ui 클래스 입니다. ")
        self.app.exec()
