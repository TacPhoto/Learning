#include "cMain.h"
#include "cSerialization.h"

wxBEGIN_EVENT_TABLE(cMain, wxFrame)
	EVT_MENU(600, OnButtonSaveClicked) // menuSave
	EVT_MENU(601, OnButtonLoadClicked) // menuLoad
	EVT_MENU(602, OnButtonRestartClicked) // menuRestart

	EVT_MENU(610, OnButtonEasyClicked) // menuEasy
	EVT_MENU(611, OnButtonNormalClicked) // menuNormal
	EVT_MENU(612, OnButtonHardClicked) // menuHard

	EVT_MENU(613, OnButtonShowHelpClicked) // menuShowHelp
	EVT_MENU(620, OnButtonShowHintClicked) // menuShowHint

#ifdef _DEBUG
	EVT_MENU(630, OnButtonDEBUGClicked) // menuShowHint
#endif // DEBUG

wxEND_EVENT_TABLE()


cMain::cMain() : wxFrame(nullptr, wxID_ANY, wxT("Minesweeper"), wxPoint(20, 20), wxSize(800, 600))
{
	// TODO: implement game saving and loading

	setMenuBar();
	level lvl;
	setLevel(easy);

	clicked = 0;

	struct lastClickedField lastClicked = {5, 5};
	lastClicked.x = 5;

	btn = new wxButton * [nFieldWidth * nFieldHeight];
	wxGridSizer* grid = new wxGridSizer(nFieldWidth, nFieldHeight, 0, 0);
	nField = new int[nFieldWidth * nFieldHeight];

	wxFont font(24, wxFONTFAMILY_DEFAULT, wxFONTSTYLE_NORMAL, wxFONTWEIGHT_BOLD, false);

	// Fill minefield with buttons
	for (int x = 0; x < nFieldWidth; x++)
	{
		for (int y = 0; y < nFieldHeight; y++)
		{
			int index = y * nFieldWidth + x;
			
			btn[index] = new wxButton(this, 10000 + (index));
			btn[index]->SetFont(font);
			btn[index]->SetLabel("");
			grid->Add(btn[y * nFieldWidth + x], 1, wxEXPAND | wxALL);

			btn[index]->Bind(wxEVT_COMMAND_BUTTON_CLICKED, &cMain::OnButtonClicked, this); 

			nField[index] = 0;
		}
	}
	
	this->SetSizer(grid);
	grid->Layout();
	
}

void cMain::setMenuBar()
{
	wxMenuBar* menuBar = new wxMenuBar;

	wxMenu* menuGameSession = new wxMenu;
	wxMenu* menuLevel = new wxMenu;

	wxMenuItem* menuSave = new wxMenuItem(menuGameSession, 600, wxT("&Save"));
	wxMenuItem* menuLoad = new wxMenuItem(menuGameSession, 601, wxT("&Load"));
	wxMenuItem* menuRestart = new wxMenuItem(menuGameSession, 602, wxT("&Reset"));

	wxMenuItem* menuEasy = new wxMenuItem(menuLevel, 610, wxT("&Easy"));
	wxMenuItem* menuNormal = new wxMenuItem(menuLevel, 611, wxT("&Normal"));
	wxMenuItem* menuHard = new wxMenuItem(menuLevel, 612, wxT("&Hard"));
	wxMenuItem* menuShowHint = new wxMenuItem(menuLevel, 620, wxT("&Show Hint"));
	wxMenuItem* menuShowHelp = new wxMenuItem(menuLevel, 613, wxT("&Show Help"));

	menuGameSession->Append(menuSave);
	menuGameSession->Append(menuLoad);
	menuGameSession->Append(menuRestart);

	menuLevel->Append(menuEasy);
	menuLevel->Append(menuNormal);
	menuLevel->Append(menuHard);
	menuLevel->Append(menuShowHint);
	menuLevel->Append(menuShowHelp);

#ifdef _DEBUG
	wxMenuItem* menuDEBUG = new wxMenuItem(menuLevel, 630, wxT("&DEBUG LEVEL"));
	menuLevel->Append(menuDEBUG);
#endif

	menuBar->Append(menuGameSession, wxT("&Game Session"));
	menuBar->Append(menuLevel, wxT("&Level"));

	this->SetMenuBar(menuBar);
	menuBar->Show(true);	
}

void cMain::OnButtonShowHintClicked(wxCommandEvent& evt)
{
	if (bFirstClick)
	{
		wxMessageBox("Don't be shy, try any field before using a hint ;)");
		evt.Skip();
		return;
	}

	int x = lastClicked.x;
	int y = lastClicked.y;

	if (hints != 0)
	{
		for (int distance = 0; distance < 10; distance++) {
			// Distance is a maximum distance for a single dimension. it is equal for X and Y
			// Corners: x1,y1 x1,y2 x2,y1 x2,y2 - we need to iterate between those cords

			int x1 = x - distance;
			int x2 = x + distance;
			int y1 = y - distance;
			int y2 = y + distance;

			bool found = false;

			for (int i = 0; i < distance * 2 + 1; i++) {
				int temp_x = x1 + i;
				int index = y1 * nFieldWidth + temp_x;

				if (index > 100)
				{
					break;
				}

				if (nField[index] == -1 && !(btn[index]->GetLabelText() == wxT("X")))
				{
					btn[index]->SetLabel("X");
					found = true;
					break;
				}
			}
			if (found) break;

			for (int i = 0; i < distance * 2 + 1; i++) {
				int temp_x = x1 + i;
				int index = y2 * nFieldWidth + temp_x;

				if (index > 100)
				{
					break;
				}

				if (nField[index] == -1 && !(btn[index]->GetLabelText() == wxT("X")))
				{
					btn[index]->SetLabel("X");
					found = true;
					break;
				}
			}
			if (found) break;

			for (int i = 0; i < distance * 2 + 1; i++) {
				int temp_y = y1 + i;
				int index = temp_y * nFieldWidth + x1;

				if (index > 100)
				{
					break;
				}

				if (nField[index] == -1 && !(btn[index]->GetLabelText() == wxT("X")))
				{
					btn[index]->SetLabel("X");
					found = true;
					break;
				}
			}
			if (found) break;

			for (int i = 0; i < distance * 2 + 1; i++) {
				int temp_y = y1 + i;
				int index = temp_y * nFieldWidth + x2;

				if (index > 100)
				{
					break;
				}

				if (nField[index] == -1 && !(btn[index]->GetLabelText() == wxT("X")))
				{
					btn[index]->SetLabel("X");
					found = true;
					break;
				}
			}
			if (found) break;

		}

		hints--;
		this->SetLabel(labelBaseTitle + wxString::Format(wxT("%d"), (int)hints));
	}
	else
	{
		wxMessageBox("No More Hints For You");
	}

	evt.Skip();
}

cMain::~cMain()
{
	delete[]btn;
	delete nField;

	delete menuEasy;
	delete menuNormal;
	delete menuHard;
	delete menuShowHelp;

	delete menuSave;
	delete menuLoad;
	delete menuRestart;

	delete menuGameSession;
	delete menuLevel;

	delete menuBar;

	delete showHint;

#ifdef _DEBUG
	delete menuDEBUG;
#endif
}

void cMain::setLevel(level l)
{
	lvl = l;
	wxString newTitle;

	switch (lvl)
	{
	case easy:
		mines = 15;
		hints = 2;
		labelBaseTitle = _("Minesweeper - Easy | Hints: ");
		newTitle = labelBaseTitle + wxString::Format(wxT("%d"), (int)hints);
		break;
	case normal:
		hints = 1;
		mines = 30;
		labelBaseTitle = _("Minesweeper - Normal | Hints: ");
		newTitle = labelBaseTitle + wxString::Format(wxT("%d"), (int)hints);
		break;
	case hard:
		hints = 0;
		mines = 45;
		labelBaseTitle = _("Minesweeper - Hard | Hints: ");
		newTitle = labelBaseTitle + wxString::Format(wxT("%d"), (int)hints);
		break;
#ifdef _DEBUG
	case debug:
		hints = 100;
		mines = 30;
		labelBaseTitle = _("Minesweeper - DEBUG LEVEL | Click 20 to win | Hints: ");
		newTitle = labelBaseTitle + wxString::Format(wxT("%d"), (int)hints);
		break;
#endif 

	}

	this->SetLabel(newTitle);


}

void cMain::OnButtonClicked(wxCommandEvent& evt)
{
	clicked++;

	// Get button coordinate
	int x = (evt.GetId() - 10000) % nFieldWidth;
	int y = (evt.GetId() - 10000) / nFieldWidth;
	int index = y * nFieldWidth + x;
	lastClicked.x = x;
	lastClicked.y = y;
	//wxMessageBox(wxString::Format(wxT("x: %d y: %d"), lastClicked.x, lastClicked.y));

	// Populate minefield
	if (bFirstClick)
	{
		populateMinefield(x, y);
	}

	//  Disable button, cannot be pressed again
	btn[index]->Enable(false);

	// Mine clicked test
	if (nField[index] == -1)
	{
		btn[index]->SetLabel("X");
		wxMessageBox("GAME OVER");

		clicked = 0;

		resetGame();
	}

	else
	{
		updateNeighbouringMinesNum(x, y);

		// Winning condition
#ifndef _DEBUG
		if (clicked == 100 - mines) 
#endif // !_DEBUG
#ifdef _DEBUG
		if (lvl == debug && clicked == 20 || clicked == 100 - mines)
#endif
		{
			wxMessageBox("YOU WON DA GAME MY DUDE");
			clicked = 0;
		}
	}

	evt.Skip();
}

void cMain::updateNeighbouringMinesNum(int x, int y)
{
	int index = y * nFieldWidth + x;
	// Count neighbourmines
	int mine_count = 0;
	for (int i = -1; i < 2; i++)
	{
		for (int j = -1; j < 2; j++)
		{
			if ((x + 1 >= 0) && (x + i < nFieldWidth) && (y + j >= 0) && (y + j < nFieldHeight))
			{
				if (nField[(y + j) * nFieldWidth + (x + i)] == -1)
				{
					mine_count++;
				}
			}
		}

		// Update button label
		if (mine_count > 0)
		{
			btn[index]->SetLabel(std::to_string(mine_count));
		}
	}
}

void cMain::resetGame()
{
	// Reset game
	bFirstClick = true;
	for (int x = 0; x < nFieldWidth; x++) {
		for (int y = 0; y < nFieldHeight; y++)
		{
			int index = y * nFieldWidth + x;

			nField[index] = 0;
			btn[index]->SetLabel("");
			btn[index]->Enable(true);
		}
	}

	lastClicked.x = 5;
	lastClicked.y = 5;
}

void cMain::populateMinefield(int x, int y)
{
	int tempMines = mines;

	while (tempMines)
	{
		int rx = rand() % nFieldWidth;
		int ry = rand() % nFieldHeight;
		int randIndex = ry * nFieldWidth + rx;

		if (nField[randIndex] == 0 && rx != x && ry != y)
		{
			nField[randIndex] = -1;
			tempMines--;
		}

		bFirstClick = false;
	}
}

void cMain::OnButtonSaveClicked(wxCommandEvent& evt)
{
	std::ofstream file;
	file.open("save.minesweeper");
	cSerialization::serialize(file, lvl, clicked, lastClicked.x, lastClicked.y, hints, btn);
	file.close();

	evt.Skip();
}

void cMain::OnButtonLoadClicked(wxCommandEvent& evt)
{
	std::ifstream file;
	file.open("save.minesweeper");

	int levelInt = lvl;
	cSerialization::deSerialize(file, levelInt, clicked, lastClicked.x, lastClicked.y, hints, btn);
	// TODO: serialize mines
	// TODO: should rewrite level valuel, set level, update hints num etc
	file.close();

	evt.Skip();
}

void cMain::OnButtonRestartClicked(wxCommandEvent& evt)
{
	resetGame();

	evt.Skip();
}

void cMain::OnButtonEasyClicked(wxCommandEvent& evt)
{
	setLevel(easy);
	resetGame();
	evt.Skip();
}

void cMain::OnButtonNormalClicked(wxCommandEvent& evt)
{
	setLevel(normal);
	resetGame();

	evt.Skip();
}

void cMain::OnButtonHardClicked(wxCommandEvent& evt)
{
	setLevel(hard);
	resetGame();

	evt.Skip();
}

#ifdef _DEBUG
void  cMain::OnButtonDEBUGClicked(wxCommandEvent& evt)
{
	setLevel(debug);
	resetGame();

	evt.Skip();
}
#endif // _DEBUG

void cMain::OnButtonShowHelpClicked(wxCommandEvent& evt)
{
	evt.Skip();
}

