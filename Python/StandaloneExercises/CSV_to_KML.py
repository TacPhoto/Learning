#CSV to KML exporter for personal use, needs a csv with specific columns, IN-PROGRESS
import pandas

base_path = u'C:\\Users\\Pan i W³adca\\Desktop\\teest.csv'
base_csv = pandas.read_csv(base_path, encoding = 'utf-8', sep = ';', engine = 'c')

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

def set_meta(csv, i):
    if 'rzemys³' in (csv.at[i, 'category']):
        category = 'Przemys³owe'
        icon = icons['industrial']
    elif 'zpital' in (csv.at[i, 'category']):
        category = 'Szpitale'
        icon = icons['medical']
    elif 'ortyfikac' in (csv.at[i, 'category']):
        category = 'Fortyfikacje, wojskowe'
        icon = icons['fortification']
    elif 'mazur' in (csv.at[i, 'category']):
        category = 'Nasze'
        icon = icons['mazur']
    elif ('szko³' in (csv.at[i, 'category'].lower())):
        category = 'Szko³y i uczelnie'
        icon = icons['school']
    elif 'otel' in (csv.at[i, 'category']):
        category = 'Hotele i oœrodki'
        icon = icons['hotel']
    elif ('domy' in (csv.at[i, 'category'].lower())):
        category = 'Hotele i oœrodki'
        icon = icons['hotel']
    elif ('restauracj' in (csv.at[i, 'category'].lower())):
        category = 'Restauracje'
        icon = icons['restaurant']
    elif ('schron' in (csv.at[i, 'category'].lower())):
        category = 'Schrony i podziemia'
        icon = icons['underground']
    elif ('kolej' in (csv.at[i, 'category'].lower())):
        category = 'Kolej'
        icon = icons['train']
    elif ('rozryw' in (csv.at[i, 'category'].lower())):
        category = 'Kolej'
        icon = icons['entertainment']
    elif ('koœci' in (csv.at[i, 'category'].lower())):
        category = 'Kolej'
        icon = icons['church']
    else:
        category = 'Brak kategorii'
        icon = icons['unkown']

    return category, icon;

for i in (base_csv.index):
    category, icon = set_meta(base_csv, i)

    descr =u"""\
{status}, {category}, {rate}/5
{description}
{phone}
    """.format(status = base_csv.at[i, 'status'],
               category = category,
               rate = len(base_csv.at[i, 'rate']),
               description = base_csv.at[i, 'description'],
               phone =  base_csv.at[i, 'phone'] )

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
    """.format(name = base_csv.at[i, 'name'],
               description = descr,
               longitude = base_csv.at[i, 'longitude'],
               latitude = base_csv.at[i, 'latitude'],
               icon = icon)
    print(single_record)
