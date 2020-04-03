import sys
import os
from PyQt5.QtWidgets import QApplication, QTextEdit, QWidget, QPushButton, QVBoxLayout, QHBoxLayout, QFileDialog

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


app = QApplication(sys.argv)
writer = Notepad()
sys.exit(app.exec_())