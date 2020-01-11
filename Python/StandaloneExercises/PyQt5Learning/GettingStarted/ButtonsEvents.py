from PyQt5 import QtWidgets
from PyQt5.QtWidgets import QApplication, QMainWindow, QLabel
import sys


class MyWindow(QMainWindow):
    def __init__(self):
        super(MyWindow, self).__init__()

        self.setGeometry(200, 200, 300, 300)
        self.setWindowTitle("Window!")

        self.initUI()

    def initUI(self):
        self.label = QLabel(self)
        self.label.setText("Some Label")
        self.label.move(50, 50)

        self.button1 = QtWidgets.QPushButton(self)
        self.button1.setText("Click Here!")
        self.button1.clicked.connect(self.clicked)

    def clicked(self):
        self.label.setText("Modified text! SOME LONG SAMPLE TEXT")
        self.update()

    def update(self):
        self.label.adjustSize()

def clicked():
    print("Clicked the button")

def main():
    app = QApplication(sys.argv)
    win = MyWindow()
    win.show()
    sys.exit(app.exec_())

if __name__ == "__main__":
    main()