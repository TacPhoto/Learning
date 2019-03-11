def f_to_c(f_temp):
	return( (f_temp - 32) * 5/9 )

def c_to_f(c_temp):
  return( (c_temp * 9/5) + 32 )

def calc_force(mass, acceleration): #use with kilograms and meters per second^2
  return (mass * acceleration)
#####
print( "100 Fahreinheit in Celcius is", f_to_c(100), "degrees" )
print( "The force of 100kg moving 14 meters a second\^2 is", calc_force(100, 14))
