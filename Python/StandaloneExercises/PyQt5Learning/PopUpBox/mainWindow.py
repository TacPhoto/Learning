# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'mainWindow.ui'
#
# Created by: PyQt5 UI code generator 5.13.2
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtWidgets import QMessageBox


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(792, 600)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.someButton = QtWidgets.QPushButton(self.centralwidget)
        self.someButton.setGeometry(QtCore.QRect(110, 70, 571, 381))
        self.someButton.setObjectName("someButton")
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtWidgets.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 792, 21))
        self.menubar.setObjectName("menubar")
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

        self.someButton.clicked.connect(self.show_popup)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.someButton.setText("Click, trust me, it's worth it!")

    def show_popup(self):
        msg = QMessageBox()
        msg.setWindowTitle("Best PopUp In The World!")
        msg.setText("I'm damn great pop up box! I told ya, ya won't regret that click")
        msg.setIcon(QMessageBox.Information)
        msg.setStandardButtons(QMessageBox.Ok | QMessageBox.Retry)
        msg.setDefaultButton(QMessageBox.Retry)
        msg.setInformativeText("See ya next time!")

        msg.setDetailedText("Gotcha!")

        msg.buttonClicked.connect(self.popup_button)

        x = msg.exec_()

    def popup_button(self, i):
        if i.text() == "Retry":
            self.show_popup()


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec_())
