import unittest as ut
from unittest.mock import patch
from . exampleClass import SomeClass

class TestExampleClass(ut.TestCase):

    def setUp(self):
        print('Executing Setup')
        self.mail = SomeClass('4', '2', 'second')
        self.mail2 = SomeClass('5', '3', 'fourth')

    def tearDown(self):
        print('Executing Teardown\n')

    def test_email(self):
        print('Email Test')
        self.assertEqual(self.mail.email, '4.2@sth.sth')
        self.assertEqual(self.mail2.email, '5.3@sth.sth')

    def test_values(self):
        print('Values Test')
        self.assertEqual(self.mail.values, '4 2 second')
        self.assertEqual(self.mail2.values, '5 3 fourth')

        self.mail.x = 7
        self.mail.y = 8
        self.mail.name = 'name2'
        self.assertEqual(self.mail.values, '7 8 name2')

    def test_addValToX(self):
        print('addValToX Test')
        self.mail.addValToX()
        self.mail2.addValToX()
        self.assertEqual(self.mail.x, 14)
        self.assertEqual(self.mail2.x, 15)


if __name__ == '__main__':
    ut.main()