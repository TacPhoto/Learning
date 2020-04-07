import sys
import os
from PyQt5.QtWidgets import QApplication, QTextEdit, QWidget, \
    QPushButton, QVBoxLayout, QHBoxLayout, QFileDialog, \
    QAction, qApp, QMainWindow


class Notepad(QWidget):
    def __init__(self):
        super(Notepad, self).__init__()
        self.text = QTextEdit(self)
        self.clr_btn = QPushButton("Clear")
        self.save_btn = QPushButton("Save")
        self.open_btn = QPushButton("Open")

        self.init_ui()

    def init_ui(self):
        v_layout = QVBoxLayout()
        h_layout = QHBoxLayout()

        h_layout.addWidget(self.clr_btn)
        h_layout.addWidget(self.save_btn)
        h_layout.addWidget(self.open_btn)

        v_layout.addWidget(self.text)
        v_layout.addLayout(h_layout)

        self.clr_btn.clicked.connect(self.clear_text)
        self.save_btn.clicked.connect(self.save_text)
        self.open_btn.clicked.connect(self.open_text)

        self.setLayout(v_layout)
        self.setWindowTitle("PyTepad")

        self.show()

    def clear_text(self):
        self.text.clear()

    def save_text(self):
        filename = QFileDialog.getSaveFileName(self, 'Save File', os.getenv('HOME'))
        with open(filename[0], 'w') as f:
            my_text = self.text.toPlainText()
            f.write(my_text)

    def open_text(self):
        filename = QFileDialog.getOpenFileName(self, 'Open File', os.getenv('HOME'))
        with open(filename[0], 'r') as f:
            file_text = f.read()
            self.text.setText(file_text)


class Writer(QMainWindow):
    def __init__(self):
        super().__init__()

        self.form_widget = Notepad()
        self.setCentralWidget(self.form_widget)

        self.init_ui()

    def init_ui(self):
        bar = self.menuBar()
        file = bar.addMenu("File")

        # Create Actions for menus
        save_action = QAction("&Save", self)
        save_action.setShortcut("Ctrl+S")

        new_action = QAction("&New", self)
        new_action.setShortcut("Ctrl+N")

        open_action = QAction("&Open", self)
        open_action.setShortcut("Ctrl+O")

        quit_action = QAction("&Quit", self)
        quit_action.setShortcut("Ctrl+Q")

        find_action = QAction("Find", self)

        replace_action = QAction("Replace", self)

        # Add Actions to menus
        file.addAction(new_action)
        file.addAction(open_action)
        file.addAction(save_action)
        file.addAction(quit_action)

        # Events
        quit_action.triggered.connect(self.quit_trigger)
        file.triggered.connect(self.respond)

        self.show()

    def quit_trigger(self):
        qApp.quit()

    def respond(self, q):
        signal = q.text()

        if signal == "New":
            self.form_widget.clear_text()
        elif signal == "&Open":
            self.form_widget.open_text()
        elif signal == "&Save":
            self.form_widget.save_text()
        elif signal == "&Open":
            self.form_widget.open_text()


app = QApplication(sys.argv)
writer = Writer()
sys.exit(app.exec_())
