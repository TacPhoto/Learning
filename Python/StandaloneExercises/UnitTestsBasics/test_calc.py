import unittest as ut
from . import calc

class TestCalc(ut.TestCase):

    def test_add(self):
        self.assertEqual(calc.add(1, 2), 3)
        self.assertEqual(calc.add(0, 1), 1)
        self.assertEqual(calc.add(0, 0), 0)
        self.assertEqual(calc.add(2, -1), 1)
        self.assertEqual(calc.add(0, -1), -1)

    def test_sub(self):
        self.assertEqual(calc.subtract(1, 2), -1)
        self.assertEqual(calc.subtract(0, 1), -1)
        self.assertEqual(calc.subtract(0, 0), 0)
        self.assertEqual(calc.subtract(2, -1), 3)
        self.assertEqual(calc.subtract(0, -1), 1)

    def test_multiply(self):
        self.assertEqual(calc.multiply(0, 0), 0)
        self.assertEqual(calc.multiply(1, 0), 0)
        self.assertEqual(calc.multiply(1, 1), 1)
        self.assertEqual(calc.multiply(2, 2), 4)
        self.assertEqual(calc.multiply(2, 0.5), 1)
        self.assertEqual(calc.multiply(1, -1),- 1)

    def test_divide(self):
        self.assertEqual(calc.divide(1, 1), 1)
        self.assertEqual(calc.divide(2, 2), 1)
        self.assertEqual(calc.divide(2, 0.5), 4)
        self.assertEqual(calc.divide(1, -1), -1)
        self.assertEqual(calc.divide(5, 2), 2.5)

        with self.assertRaises(ValueError):
            calc.divide(0, 0)
            calc.divide(1, 0)







if __name__ == '__main__':
    ut.main()