import sys
from PyQt5 import QtWidgets, QtGui, QtCore, Qt


class MainWindow:
    def __init__(self):
        self.app = QtWidgets.QApplication(sys.argv)
        self.window = QtWidgets.QMainWindow()

        self.imagePath = "githubLogo.png"

        self.initGui()

        self.window.setWindowTitle("Whatever")
        self.window.setGeometry(0, 0, 270, 500)
        self.window.setStyleSheet("border:3px solid #4e4e4e; background-color: #6e6e6e")

        self.window.show()
        sys.exit(self.app.exec_())

    def initGui(self):
        self.applyButton = QtWidgets.QPushButton("Apply", self.window)
        self.applyButton.setGeometry(140, 420, 120, 30)
        self.applyButton.setStyleSheet("background-color: #4e4e4e; color: #f7f7f7")

        self.cancelButton = QtWidgets.QPushButton("Cancel", self.window)
        self.cancelButton.setGeometry(10, 420, 120, 30)
        self.cancelButton.setStyleSheet("background-color: #4e4e4e; color: #f7f7f7")

        #label that holds the image
        self.label = QtWidgets.QLabel(self.window)
        self.label.setGeometry(35, 50, 200, 200)
        self.label.setAutoFillBackground(False)

        #image
        self.image = QtGui.QImage(self.imagePath)
        self.pixmapImage = QtGui.QPixmap.fromImage(self.image)

        #display image in the label
        self.label.setPixmap(self.pixmapImage)
        self.label.setScaledContents(True)

main = MainWindow()
