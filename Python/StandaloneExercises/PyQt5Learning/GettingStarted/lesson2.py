from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *

import sys


class MainWindow(QMainWindow):

    def __init__(self, *args, **kwargs):
        super(MainWindow, self).__init__(*args, **kwargs)

       # self.windowTitleChanged.connect(lambda x: self.my_custom_fn(x, 10))
        self.setWindowTitle("Window name!")

        layout = QHBoxLayout()

        for n in range(10):
            btn = QPushButton(str(n))
            btn.pressed.connect(lambda n=n: self.my_custom_fn(n))
            layout.addWidget(btn)

        widget = QWidget()
        widget.setLayout(layout)

        self.setCentralWidget(widget)

    def onWindowTitleChange(self, s):
        print(s)

    """def my_custom_fn(selfself, a="Hello", b=5):
        print(a, b)"""

    def my_custom_fn(selfself, a):
        print(a)


app = QApplication(sys.argv)

window = MainWindow()
window.show()

app.exec_()