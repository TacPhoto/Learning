import sys
from PyQt5 import QtWidgets, QtGui, QtCore, Qt


class MainWindow:
    def __init__(self):
        self.app = QtWidgets.QApplication(sys.argv)
        self.window = QtWidgets.QMainWindow()

        self.imagePath = "githubLogo.png"

        self.initGui()

        self.window.setWindowTitle("Whatever")
        self.window.setGeometry(0, 0, 270, 410)
        self.window.setStyleSheet("background-color: #6e6e6e") #border:3px solid #4e4e4e;

        self.window.show()
        sys.exit(self.app.exec_())

    def initGui(self):

        #login button
        self.loginButton = QtWidgets.QPushButton("Login", self.window)
        self.loginButton.setGeometry(140, 360, 120, 30)
        self.loginButton.setStyleSheet("background-color: #383737;"
                                       "color: #fefafa;"
                                       "font-size: 15px;"
                                       "border: 1px solid #4e4e4e")

        #register button
        self.registerButton = QtWidgets.QPushButton("Register", self.window)
        self.registerButton.setGeometry(10, 360, 120, 30)
        self.registerButton.setStyleSheet("background-color: #383737;"
                                       "color: #fefafa;"
                                       "font-size: 15px;"
                                       "border: 1px solid #4e4e4e")

        #label that holds the image
        self.label = QtWidgets.QLabel(self.window)
        self.label.setGeometry(35, 20, 200, 200)
        self.label.setStyleSheet("background-color: #6e6e6e")
        self.label.setAutoFillBackground(False)

        #pseudo text field
        self.pseudo = QtWidgets.QTextEdit(self.window)
        self.pseudo.setGeometry(25, 230, 220, 30)
        self.pseudo.setText("Some text")
        self.pseudo.setStyleSheet("background-color: #f7f7f7;"
                                  "color: #8e8e8e;"
                                  "padding-top: 3px;"
                                  "padding-left: 10px")

        #email text field
        self.email = QtWidgets.QTextEdit(self.window)
        self.email.setGeometry(25, 270, 220, 30)
        self.email.setText("Email")
        self.email.setStyleSheet("background-color: #f7f7f7;"
                                  "color: #8e8e8e;"
                                  "padding-top: 3px;"
                                  "padding-left: 10px")

        # pseudo password text field
        self.password = QtWidgets.QTextEdit(self.window)
        self.password.setGeometry(25, 310, 220, 30)
        self.password.setText("Password")
        self.password.setStyleSheet("background-color: #f7f7f7; color: #8e8e8e; padding-top: 3px; padding-left: 10px")

        #image
        self.image = QtGui.QImage(self.imagePath)
        self.pixmapImage = QtGui.QPixmap.fromImage(self.image)

        #display image in the label
        self.label.setPixmap(self.pixmapImage)
        self.label.setScaledContents(True)

main = MainWindow()
