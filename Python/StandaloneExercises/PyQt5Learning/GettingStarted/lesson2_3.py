from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *

import sys


class MainWindow(QMainWindow):

    def __init__(self, *args, **kwargs):
        super(MainWindow, self).__init__(*args, **kwargs)

        self.windowTitleChanged.connect(self.onWindowTitleChange)
        self.setWindowTitle("Window name!")

        label = QLabel("Some text")
        label.setAlignment(Qt.AlignCenter)

        self.setCentralWidget(label)

    def contextMenuEvent(self, e):
        print("Context menu requested")

    def onWindowTitleChange(self, s):
        print(s)


app = QApplication(sys.argv)

window = MainWIndow()
window.show()

app.exec_()