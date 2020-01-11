from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *

import sys


class MainWindow(QMainWindow):

    some_signal = pyqtSignal(str)

    def __init__(self, *args, **kwargs):
        super(MainWindow, self).__init__(*args, **kwargs)

        self.setWindowTitle("Window name!")

        button = QPushButton("HEY!")
        button.pressed.connect(self.pushed_my_button)

        self.some_signal.connect(self.caught_signal)
        self.setCentralWidget(button)

    def pushed_my_button(self):
        print("Pressed the button!")
        self.some_signal.emit("WUSH!")

    def caught_signal(self, a):
        print(a)

app = QApplication(sys.argv)

window = MainWIndow()
window.show()

app.exec_()