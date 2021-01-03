#pragma once
#include "wx/wx.h"
#include <iostream>
#include <fstream>

namespace cSerialization
{
	bool serialize(std::ofstream &outputFile, int lvl, int clicked, int x, int y, int hints, wxButton *btn[], int nField[]);

	bool deSerialize(std::ifstream &outputFile, int &lvl, int &clicked, int &x, int &y, int &hints, wxButton* btn[], int nField[]);
}

