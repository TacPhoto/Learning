# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'window.ui'
#
# Created by: PyQt5 UI code generator 5.13.2
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtGui, QtWidgets


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(800, 602)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.photo = QtWidgets.QLabel(self.centralwidget)
        self.photo.setGeometry(QtCore.QRect(0, 0, 801, 381))
        self.photo.setText("")
        self.photo.setPixmap(QtGui.QPixmap("pic1.png"))
        self.photo.setScaledContents(True)
        self.photo.setObjectName("photo")
        self.pic1_button = QtWidgets.QPushButton(self.centralwidget)
        self.pic1_button.setGeometry(QtCore.QRect(30, 430, 241, 121))
        self.pic1_button.setObjectName("pic1_button")
        self.pic2_button = QtWidgets.QPushButton(self.centralwidget)
        self.pic2_button.setGeometry(QtCore.QRect(520, 430, 241, 121))
        self.pic2_button.setObjectName("pic2_button")
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtWidgets.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 800, 21))
        self.menubar.setObjectName("menubar")
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

        self.pic1_button.clicked.connect(self.show_pic1)
        self.pic2_button.clicked.connect(self.show_pic2)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.pic1_button.setText(_translate("MainWindow", "BEAUTIFUL PIC 1"))
        self.pic2_button.setText(_translate("MainWindow", "BEAUTIFUL PIC 2"))

    def show_pic1(self):
        self.photo.setPixmap(QtGui.QPixmap("pic1.png"))

    def show_pic2(self):
        self.photo.setPixmap(QtGui.QPixmap("pic2.png"))


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec_())
