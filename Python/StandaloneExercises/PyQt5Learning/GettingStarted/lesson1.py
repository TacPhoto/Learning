from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *

import sys


class MainWIndow(QMainWindow):
    def __init__(self, *args, **kwargs):
        super(MainWIndow, self).__init__(*args, **kwargs)

        self.setWindowTitle("Window name!")

        label = QLabel("Some text")
        label.setAlignment(Qt.AlignCenter)

        self.setCentralWidget(label)

app = QApplication(sys.argv)

window = MainWIndow()
window.show()

app.exec_()