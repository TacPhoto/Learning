#CSV to KML exporter for personal use, needs a csv with specific columns
import pandas, codecs, logging, timeit
logging.basicConfig(level = logging.DEBUG)

start = timeit.default_timer()
desktop_path = u'C:\\Users\\Pan i W³adca\\Desktop\\'
csv_name = 'teest.csv'
finalname = 'urb_mapka.kml'
try:
    base_csv = pandas.read_csv(desktop_path + csv_name, encoding = 'utf-8', sep = ';', engine = 'c')
except Exception as e:
    logging.error('Could not read the CSV file', exc_info=True)
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
    'house' : 'm_ylw-pushpin1',
    'restaurant' : 'msn_dining0',
    'train' : 'msn_rail',
    'underground' : 'msn_blu-diamond',
    'no_longer' : 'msh_shaded_do',
    'entertainment' : 'msn_arts',
    'church' : 'm_ylw-pushpin0',
    'office' : 'm_ylw-pushpin00'
}

rows_number = base_csv.index
name = tuple(base_csv['name'])
status = tuple(base_csv['status'])
rate = tuple((base_csv['rate']))
description = tuple(base_csv['description'])
phone = tuple(base_csv['phone'])
categories = tuple(base_csv['category'])
longitude = tuple(base_csv['longitude'])
latitude = tuple(base_csv['latitude'])

del base_csv

def set_meta(category):
    if 'przemys³' in category:
        category = 'Przemys³owe'
        icon = icons['industrial']
    elif 'szpital' in category:
        category = 'Medyczne'
        icon = icons['medical']
    elif ('fortyfikac' in category) or ('wojsko' in category):
        category = 'Fortyfikacje, wojskowe'
        icon = icons['fortification']
    elif 'mazur' in category:
        category = 'Nasze'
        icon = icons['mazur']
    elif 'szko³' in  category:
        category = 'Szko³y, Naukowe, Uczelnie'
        icon = icons['school']
    elif 'otel' in category:
        category = 'Hotele i oœrodki'
        icon = icons['hotel']
    elif 'dom' in category or 'dwor' in category:
        category = 'Domy, dwory i pa³ace'
        icon = icons['house']
    elif 'restauracj' in category:
        category = 'Restauracje i kluby'
        icon = icons['restaurant']
    elif ('schron' in category) or ('podziem' in category):
        category = 'Schrony i podziemia'
        icon = icons['underground']
    elif 'kolej' in category or 'transport' in category:
        category = 'Transport'
        icon = icons['train']
    elif 'rozryw' in category:
        category = 'Rozrywka'
        icon = icons['entertainment']
    elif 'koœci' in category:
        category = 'Religijne'
        icon = icons['church']
    elif 'biurow' in category:
        category = 'Biurowe, Wie¿owce'
        icon = icons['office']

    else:
        category = 'Brak kategorii'
        icon = icons['unkown']

    return category, icon


our = str()

industrial, medical, fortification, office,\
science, house, hotel, restaurant,\
underground, transport, entertainment, religious,\
none = str(),str(),str(),str(),   str(), str(),str(),str(),   str(),str(),str(),str(),   str()

industrial_old, medical_old, fortification_old, office_old,\
science_old, house_old, hotel_old, restaurant_old,\
underground_old, transport_old, entertainment_old, religious_old,\
none_old = str(),str(),str(),str(),   str(), str(),str(),str(),   str(),str(),str(),str(),   str()

for i in rows_number:
    logging.debug(  'Starting %d Rate = %d',(i + 2),(len(str(rate[i]))))
    category, icon = set_meta(str(categories[i]).lower())

    description_final = u"""\
{status}, {category}, {rate}/5
{description}
{phone}
    """.format(status = status[i],
               category = category,
               rate = (len(str(rate[i])) if not pandas.isnull(rate[i]) else '1'),
               description = (description[i] if not pandas.isnull(description[i]) else ''),
               phone =  (phone[i] if not pandas.isnull(phone[i]) else ''))

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
               description = description_final,
               longitude = longitude[i],
               latitude = latitude[i],
               icon = icon)

    if category == 'Nasze':
        our += single_record
    else:
        if 'niedo' in (str(status[i])).lower():
            if category == 'Przemys³owe':
                industrial_old += single_record
            elif category == 'Medyczne':
                medical_old += single_record
            elif category == 'Fortyfikacje, wojskowe':
                fortification_old += single_record
            elif category == 'Szko³y, Naukowe, Uczelnie':
                science_old += single_record
            elif category == 'Hotele i oœrodki':
                hotel_old += single_record
            elif category == 'Domy, dwory i pa³ace':
                house_old += single_record
            elif category == 'Restauracje i kluby':
                restaurant_old += single_record
            elif category == 'Schrony i podziemia':
                underground_old += single_record
            elif category == 'Transport':
                transport_old += single_record
            elif category == 'Rozrywka':
                entertainment_old += single_record
            elif category == 'Religijne':
                religious_old += single_record
            elif category == 'Biurowe, Wie¿owce':
                office_old += single_record
            elif category == 'Brak kategorii':
                none_old += single_record
        else:
            if category == 'Przemys³owe':
                industrial += single_record
            elif category == 'Medyczne':
                medical += single_record
            elif category == 'Fortyfikacje, wojskowe':
                fortification += single_record
            elif category == 'Szko³y, Naukowe, Uczelnie':
                science += single_record
            elif category == 'Hotele i oœrodki':
                hotel += single_record
            elif category == 'Domy, dwory i pa³ace':
                house += single_record
            elif category == 'Restauracje i kluby':
                restaurant += single_record
            elif category == 'Schrony i podziemia':
                underground += single_record
            elif category == 'Transport':
                transport += single_record
            elif category == 'Rozrywka':
                entertainment += single_record
            elif category == 'Religijne':
                religious += single_record
            elif category == 'Biurowe, Wie¿owce':
                office += single_record
            elif category == 'Brak kategorii':
                none += single_record

    logging.info('Finished %d', (i + 2))

header = u"""<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2" xmlns:gx="http://www.google.com/kml/ext/2.2" xmlns:kml="http://www.opengis.net/kml/2.2" xmlns:atom="http://www.w3.org/2005/Atom">
<Document>
	<name>{finalname}</name>
""".format(finalname = finalname)
icon_header =u"""	<Style id="sh_ranger_station">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/ranger_station.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="s_ylw-pushpin">
		<IconStyle>
			<scale>1.1</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/paddle/ltblu-blank.png</href>
			</Icon>
			<hotSpot x="32" y="1" xunits="pixels" yunits="pixels"/>
		</IconStyle>
		<ListStyle>
			<ItemIcon>
				<href>http://maps.google.com/mapfiles/kml/paddle/ltblu-blank-lv.png</href>
			</ItemIcon>
		</ListStyle>
	</Style>
	<StyleMap id="m_ylw-pushpin">
		<Pair>
			<key>normal</key>
			<styleUrl>runtime://clone14#s_ylw-pushpin</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>runtime://clone14#s_ylw-pushpin_hl</styleUrl>
		</Pair>
	</StyleMap>
	<StyleMap id="msn_blu-diamond">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_blu-diamond</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_blu-diamond</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="sh_polygon">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/polygon.png</href>
			</Icon>
		</IconStyle>
	</Style>
	<Style id="sh_blu-diamond">
		<IconStyle>
			<scale>1.3</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/paddle/blu-diamond.png</href>
			</Icon>
			<hotSpot x="32" y="1" xunits="pixels" yunits="pixels"/>
		</IconStyle>
		<ListStyle>
			<ItemIcon>
				<href>http://maps.google.com/mapfiles/kml/paddle/blu-diamond-lv.png</href>
			</ItemIcon>
		</ListStyle>
	</Style>
	<StyleMap id="m_ylw-pushpin1">
		<Pair>
			<key>normal</key>
			<styleUrl>#s_ylw-pushpin00</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#s_ylw-pushpin_hl1</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="sn_red-pushpin">
		<IconStyle>
			<scale>1.1</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/pushpin/red-pushpin.png</href>
			</Icon>
			<hotSpot x="20" y="2" xunits="pixels" yunits="pixels"/>
		</IconStyle>
	</Style>
	<Style id="s_ylw-pushpin_hl">
		<IconStyle>
			<scale>1.3</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/paddle/ltblu-blank.png</href>
			</Icon>
			<hotSpot x="32" y="1" xunits="pixels" yunits="pixels"/>
		</IconStyle>
		<ListStyle>
			<ItemIcon>
				<href>http://maps.google.com/mapfiles/kml/paddle/ltblu-blank-lv.png</href>
			</ItemIcon>
		</ListStyle>
	</Style>
	<StyleMap id="msn_polygon">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_polygon</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_polygon</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="s_ylw-pushpin_hl0">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/square.png</href>
			</Icon>
		</IconStyle>
	</Style>
	<Style id="sn_ranger_station">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/ranger_station.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="s_ylw-pushpin_hl1">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/homegardenbusiness.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sn_blu-diamond">
		<IconStyle>
			<scale>1.1</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/paddle/blu-diamond.png</href>
			</Icon>
			<hotSpot x="32" y="1" xunits="pixels" yunits="pixels"/>
		</IconStyle>
		<ListStyle>
			<ItemIcon>
				<href>http://maps.google.com/mapfiles/kml/paddle/blu-diamond-lv.png</href>
			</ItemIcon>
		</ListStyle>
	</Style>
	<StyleMap id="msn_shaded_dot">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_shaded_dot</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_shaded_dot</styleUrl>
		</Pair>
	</StyleMap>
	<StyleMap id="msn_ranger_station">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_ranger_station</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_ranger_station</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="sn_mechanic">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/mechanic.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<StyleMap id="m_ylw-pushpin0">
		<Pair>
			<key>normal</key>
			<styleUrl>#s_ylw-pushpin</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#s_ylw-pushpin_hl</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="sn_rail">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/rail.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<StyleMap id="msn_info_circle">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_info_circle</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_info_circle</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="sn_arts">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/arts.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sn_dining">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/dining.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sh_rail">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/rail.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<StyleMap id="msn_arts">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_arts</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_arts</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="sh_arts">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/arts.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<StyleMap id="msn_rail">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_rail</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_rail</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="sh_info_circle">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/info_circle.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sh_lodging">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/lodging.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sn_polygon">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/polygon.png</href>
			</Icon>
		</IconStyle>
	</Style>
	<Style id="sn_info_circle">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/info_circle.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sn_lodging">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/lodging.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sh_dining0">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/dining.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<StyleMap id="msn_mechanic">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_mechanic</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_mechanic</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="sh_shaded_dot">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/shaded_dot.png</href>
			</Icon>
		</IconStyle>
	</Style>
	<StyleMap id="msn_phone">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_phone</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_phone</styleUrl>
		</Pair>
	</StyleMap>
	<StyleMap id="msn_lodging">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_lodging</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_lodging</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="sh_phone">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/phone.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sh_mechanic">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/mechanic.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sh_red-pushpin">
		<IconStyle>
			<scale>1.3</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/pushpin/red-pushpin.png</href>
			</Icon>
			<hotSpot x="20" y="2" xunits="pixels" yunits="pixels"/>
		</IconStyle>
	</Style>
	<Style id="sn_phone">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/phone.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sn_shaded_dot">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/shaded_dot.png</href>
			</Icon>
		</IconStyle>
	</Style>
	<StyleMap id="msn_dining0">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_dining</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_dining0</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="s_ylw-pushpin0">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/square.png</href>
			</Icon>
		</IconStyle>
	</Style>
	<StyleMap id="m_ylw-pushpin00">
		<Pair>
			<key>normal</key>
			<styleUrl>#s_ylw-pushpin0</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#s_ylw-pushpin_hl0</styleUrl>
		</Pair>
	</StyleMap>
	<StyleMap id="msn_red-pushpin">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_red-pushpin</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_red-pushpin</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="s_ylw-pushpin00">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/homegardenbusiness.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>"""
bottom = """
</Document>
</kml>
"""
folder_header = u"""
	<Folder>
		<name>{folder_name}</name>
"""
subfolder_header = u"""
		<Folder>
			<name>{folder_name}</name>
"""

logging.info('SAVING FILE')
file = codecs.open(desktop_path + finalname, 'w', 'utf-8')

file.write(
    header + icon_header + folder_header.format(folder_name = 'Dostêpne i Niewiadome') +
    subfolder_header.format(folder_name = 'Przemys³owe') + industrial + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Medyczne') + medical + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Fortyfikacje, wojskowe') + fortification + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Biurowe, Wie¿owce') + office + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Szko³y, Naukowe, Uczelnie') + science + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Domy, dwory i pa³ace') + house + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Hotele i oœrodki') + hotel + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Restauracje i kluby') + restaurant + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Schrony i podziemia') + underground + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Transport') + transport + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Rozrywka') + entertainment + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Religijne') + religious + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Brak kategorii') + none + u'	</Folder>' +
    u'</Folder>' +
    folder_header.format(folder_name = 'Nie Dostêpne') +
    subfolder_header.format(folder_name = 'Przemys³owe') + industrial_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Medyczne') + medical_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Fortyfikacje, wojskowe') + fortification_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Biurowe, Wie¿owce') + office_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Szko³y, Naukowe, Uczelnie') + science_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Domy, dwory i pa³ace') + house_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Hotele i oœrodki') + hotel_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Restauracje i kluby') + restaurant_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Schrony i podziemia') + underground_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Transport') + transport_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Rozrywka') + entertainment_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Religijne') + religious_old + u'	</Folder>' +
    subfolder_header.format(folder_name = 'Brak kategorii') + none_old + u'	</Folder>' +
    u'</Folder>' +
    folder_header.format(folder_name = 'Nasze') + our + u'</Folder>' + bottom   )

file.close()
logging.info('FILE SAVED')

end = timeit.default_timer()
time_elapsed = end - start
print('TIME ELAPSED = ' + str(time_elapsed))
#print(header + icon_header + folder1_header + places + u'</Folder>' + folder2_header + places_old + u'</Folder>' + folder3_header + places_our + u'</Folder>' + bottom) #KML FILE CONSOLE OUTPUT