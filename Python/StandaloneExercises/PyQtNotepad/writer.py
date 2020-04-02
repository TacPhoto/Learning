import sys
from PyQt5.QtWidgets import QApplication, QTextEdit, QWidget, QPushButton, QVBoxLayout

class Notepad(QWidget):
    def __init__(self):
        super(Notepad, self).__init__()
        self.text = QTextEdit(self)
        self.clr_btn = QPushButton("Clear")
        self.save_btn = QPushButton("Save")

        self.init_ui()

    def init_ui(self):
        layout = QVBoxLayout()
        layout.addWidget(self.text)
        layout.addWidget(self.clr_btn)
        layout.addWidget(self.save_btn)
        self.clr_btn.clicked.connect(self.clear_text)
        self.save_btn.clicked.connect(self.save_text)

        self.setLayout(layout)
        self.setWindowTitle("PyTepad")

        self.show()

    def clear_text(self):
        self.text.clear()

    def save_text(self):
        with open("testFile.txt", "w") as f:
            text = self.text.toPlainText()
            f.write(text)



app = QApplication(sys.argv)
writer = Notepad()
sys.exit(app.exec_())