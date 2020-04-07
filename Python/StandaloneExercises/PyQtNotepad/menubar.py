import sys
import os
from PyQt5.QtWidgets import QApplication, QTextEdit, QWidget, QPushButton, QVBoxLayout, QHBoxLayout, QFileDialog, \
    QAction, qApp, QMainWindow


class Menu(QMainWindow):
    def __init__(self):
        super().__init__()

        # Create Menu bar
        bar = self.menuBar()

        # Create Root Menu
        file = bar.addMenu("File")
        edit = bar.addMenu("Edit")

        # Create Actions for menus
        save_action = QAction("Save", self)
        save_action.setShortcut("Ctrl+S")

        new_action = QAction("New", self)
        new_action.setShortcut("Ctrl+N")

        quit_action = QAction("Quit", self)
        quit_action.setShortcut("Ctrl+Q")

        find_action = QAction("Find", self)

        replace_action = QAction("Replace", self)

        # Add Actions to menus
        file.addAction(new_action)
        file.addAction(save_action)
        file.addAction(quit_action)

        find_menu = edit.addMenu("Find")
        find_menu.addAction(find_action)
        find_menu.addAction(replace_action)

        # Events
        quit_action.triggered.connect(self.quit_trigger)
        file.triggered.connect(self.selected)

        self.setWindowTitle("Menu")
        self.resize(600, 800)

        self.show()

    def quit_trigger(self):
        qApp.quit()

    def selected(self, q):
        print(q.text() + " selected")


app = QApplication(sys.argv)
menus = Menu()
sys.exit(app.exec_())
