#pragma once
#include "wx/wx.h"
#include <iostream>
#include <fstream>
/*
#ifndef _DEBUG
enum level { easy, normal, hard };
#endif // ! _DEBUG
#ifdef _DEBUG
enum level { easy, normal, hard, debug };
#endif 
*/

namespace cSerialization
{
	bool serialize(std::ofstream &outputFile, int lvl, int clicked, int x, int y, int hints, wxButton *btn[], int nField[]);

	bool deSerialize(std::ifstream &outputFile, int &lvl, int &clicked, int &x, int &y, int &hints, wxButton* btn[], int nField[]);
}

