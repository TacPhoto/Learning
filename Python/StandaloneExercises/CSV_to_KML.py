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

def set_meta(csv, row):
    if 'rzemys³' in (csv['category'].iloc[row]):
        category = 'Przemys³owe'
        icon = icons['industrial']
    elif 'zpital' in (csv['category'].iloc[row]):
        category = 'Szpitale'
        icon = icons['medical']
    elif 'ortyfikac' in (csv['category'].iloc[row]):
        category = 'Fortyfikacje, wojskowe'
        icon = icons['fortification']
    elif 'mazur' in (csv['category'].iloc[row]):
        category = 'Nasze'
        icon = icons['mazur']
    elif 'szko³' in (csv['category'].iloc[row].lower()):
        category = 'Szko³y i uczelnie'
        icon = icons['school']
    elif 'otel' in (csv['category'].iloc[row]):
        category = 'Hotele i oœrodki'
        icon = icons['hotel']
    elif ('domy' in (csv['category'].iloc[row]).lower()):
        category = 'Hotele i oœrodki'
        icon = icons['hotel']
    elif ('restauracj' in (csv['category'].iloc[row]).lower()):
        category = 'Restauracje'
        icon = icons['restaurant']
    elif ('schron' in (csv['category'].iloc[row]).lower()):
        category = 'Schrony i podziemia'
        icon = icons['underground']
    elif ('kolej' in (csv['category'].iloc[row]).lower()):
        category = 'Kolej'
        icon = icons['train']
    elif ('rozryw' in (csv['category'].iloc[row]).lower()):
        category = 'Kolej'
        icon = icons['entertainment']
    elif ('koœci' in (csv['category'].iloc[row]).lower()):
        category = 'Kolej'
        icon = icons['church']
    else:
        category = 'Brak kategorii'
        icon = icons['unkown']
    #print(category, icon) #DEBUG
    return category, icon;

for i in (base_csv.index):
    #print(i)
    category, icon = set_meta(base_csv, i)
    #print(category, icon)

    descr =u"""\
{status}, {category}, {rate}/5
{description}
{phone}
    """.format(status = base_csv['status'].iloc[i],
               category = category,
               rate = len( base_csv['rate'].iloc[i]),
               description = base_csv['description'].iloc[i],
               phone =  base_csv['phone'].iloc[i] )

    single_record = u"""
		<Placemark>
			<name>{name}</name>
			<description>{description}<description>
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
    """.format(name = base_csv['name'].iloc[i],
               description = descr,
               longitude = base_csv['longitude'].iloc[i],
               latitude = base_csv['latitude'].iloc[i],
               icon = icon)
    print(single_record)
