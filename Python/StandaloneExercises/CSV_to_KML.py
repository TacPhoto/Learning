#CSV to KML exporter for personal use, needs a csv with specific columns, IN-PROGRESS
import pandas

base_path = u'C:\\Users\\Pan i W³adca\\Desktop\\teest.csv'
try:
    base_csv = pandas.read_csv(base_path, encoding = 'utf-8', sep = ';', engine = 'c')
except Exception as e:
    print('Could not read the CSV file')
    exit()

icons = {
    'industrial' : 'msn_mechanic',
    'medical' : 'msn_phone',
    'fortification' : 'msn_ranger_station',
    'mazur' : 'msn_red-pushpin',
    'unkown' : 'msn_polygon',
    'not_verified' : 'msn_info_circle',
    'school' : 'm_ylw-pushpin',
    'hotel' : 'msn_lodging',
    'house' : 'ms_ylw-pushpinl',
    'restaurant' : 'msn_dining',
    'train' : 'msn_rail',
    'underground' : 'msn_blu-diamond',
    'no_longer' : 'msh_shaded_do',
    'entertainment' : 'msn_arts',
    'church' : 'm_ylw-pushpin'
}

rows_number = base_csv.index
name = tuple(base_csv['name'])
status = tuple(base_csv['status'])
rate = tuple(base_csv['rate'])
description = tuple(base_csv['description'])
phone = tuple(base_csv['phone'])
category = tuple(base_csv['category'])
longitude = tuple(base_csv['longitude'])
latitude = tuple(base_csv['latitude'])

del base_csv

def set_meta(category, i):
    if 'rzemys³' in category[i]:
        category = 'Przemys³owe'
        icon = icons['industrial']
    elif 'zpital' in category[i]:
        category = 'Szpitale'
        icon = icons['medical']
    elif 'ortyfikac' in  category[i]:
        category = 'Fortyfikacje, wojskowe'
        icon = icons['fortification']
    elif 'mazur' in category[i]:
        category = 'Nasze'
        icon = icons['mazur']
    elif 'szko³' in  category[i].lower():
        category = 'Szko³y i uczelnie'
        icon = icons['school']
    elif 'otel' in category[i]:
        category = 'Hotele i oœrodki'
        icon = icons['hotel']
    elif 'domy' in category[i].lower():
        category = 'Hotele i oœrodki'
        icon = icons['hotel']
    elif 'restauracj' in category[i].lower():
        category = 'Restauracje'
        icon = icons['restaurant']
    elif 'schron' in category[i].lower():
        category = 'Schrony i podziemia'
        icon = icons['underground']
    elif 'kolej' in category[i].lower():
        category = 'Kolej'
        icon = icons['train']
    elif 'rozryw' in category[i].lower():
        category = 'Kolej'
        icon = icons['entertainment']
    elif 'koœci' in category[i].lower():
        category = 'Kolej'
        icon = icons['church']
    else:
        category = 'Brak kategorii'
        icon = icons['unkown']

    return category, icon;

for i in rows_number:
    category, icon = set_meta(category, i)

    descr =u"""\
{status}, {category}, {rate}/5
{description}
{phone}
    """.format(status = status[i],
               category = category,
               rate = len(rate[i]),
               description = description[i],
               phone =  phone[i] )

    single_record = u"""
		<Placemark>
			<name>{name}</name>
			<description>{description}</description>
			<LookAt>
				<longitude>{longitude}</longitude>
				<latitude>{latitude}</latitude>
				<altitude>0</altitude>
				<heading>-2</heading>
				<tilt>0</tilt>
				<range>165</range>
				<gx:altitudeMode>relativeToSeaFloor</gx:altitudeMode>
			</LookAt>
			<styleUrl>#{icon}</styleUrl>
			<Point>
				<gx:drawOrder>1</gx:drawOrder>
				<coordinates>{longitude},{latitude}</coordinates>
			</Point>
		</Placemark>
    """.format(name = name[i],
               description = descr,
               longitude = longitude[i],
               latitude = latitude[i],
               icon = icon)
    print(single_record)
