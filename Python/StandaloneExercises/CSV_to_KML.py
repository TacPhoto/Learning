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

places = str(None)
places_old = str(None)

for i in rows_number:
    category, icon = set_meta(category, i)

    description_final = u"""\
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
               description = description_final,
               longitude = longitude[i],
               latitude = latitude[i],
               icon = icon)

    if not 'niedo' in status[i].lower():
        places += single_record
    else:
        places_old += single_record

header = """
<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2" xmlns:gx="http://www.google.com/kml/ext/2.2" xmlns:kml="http://www.opengis.net/kml/2.2" xmlns:atom="http://www.w3.org/2005/Atom">
<Document>
	<name>{finalname}</name>
""".format(finalname = 'urb_mapka.kml')
icon_header ="""
	<Style id="sh_lodging">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/lodging.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sh_ranger_station">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/ranger_station.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sh_dining">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/dining.png</href>
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
	<Style id="sh_info_circle">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/info_circle.png</href>
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
	<Style id="sh_mechanic">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/mechanic.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="s_ylw-pushpin">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/homegardenbusiness.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="s_ylw-pushpin0">
		<IconStyle>
			<scale>1.1</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/pushpin/wht-pushpin.png</href>
			</Icon>
			<hotSpot x="20" y="2" xunits="pixels" yunits="pixels"/>
		</IconStyle>
		<ListStyle>
		</ListStyle>
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
	<Style id="sh_polygon">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/polygon.png</href>
			</Icon>
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
	<Style id="s_ylw-pushpin_hl">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/homegardenbusiness.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<StyleMap id="m_ylw-pushpin1">
		<Pair>
			<key>normal</key>
			<styleUrl>#s_ylw-pushpin</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#s_ylw-pushpin_hl</styleUrl>
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
		<BalloonStyle>
		</BalloonStyle>
		<ListStyle>
		</ListStyle>
	</Style>
	<StyleMap id="msn_dining">
		<Pair>
			<key>normal</key>
			<styleUrl>#sn_dining</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#sh_dining</styleUrl>
		</Pair>
	</StyleMap>
	<Style id="sn_lodging">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/lodging.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
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
	<Style id="sh_shaded_dot">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/shaded_dot.png</href>
			</Icon>
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
	<Style id="sn_red-pushpin">
		<IconStyle>
			<scale>1.1</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/pushpin/red-pushpin.png</href>
			</Icon>
			<hotSpot x="20" y="2" xunits="pixels" yunits="pixels"/>
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
	<Style id="sn_rail">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/rail.png</href>
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
	<Style id="sh_arts">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/arts.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
		<BalloonStyle>
		</BalloonStyle>
		<ListStyle>
		</ListStyle>
	</Style>
	<StyleMap id="m_ylw-pushpin">
		<Pair>
			<key>normal</key>
			<styleUrl>#s_ylw-pushpin0</styleUrl>
		</Pair>
		<Pair>
			<key>highlight</key>
			<styleUrl>#s_ylw-pushpin_hl0</styleUrl>
		</Pair>
	</StyleMap>
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
	<Style id="sh_rail">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/rail.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
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
	<Style id="sn_ranger_station">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/ranger_station.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
	<Style id="sh_phone">
		<IconStyle>
			<scale>1.4</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/phone.png</href>
			</Icon>
			<hotSpot x="0.5" y="0" xunits="fraction" yunits="fraction"/>
		</IconStyle>
	</Style>
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
	<Style id="s_ylw-pushpin_hl0">
		<IconStyle>
			<scale>1.3</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/pushpin/wht-pushpin.png</href>
			</Icon>
			<hotSpot x="20" y="2" xunits="pixels" yunits="pixels"/>
		</IconStyle>
		<ListStyle>
		</ListStyle>
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
	<Style id="sn_mechanic">
		<IconStyle>
			<scale>1.2</scale>
			<Icon>
				<href>http://maps.google.com/mapfiles/kml/shapes/mechanic.png</href>
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
	<Style id="sn_info_circle">
"""
bottom = """
/Document>
</kml>
"""

print(places)
