#pragma once

#include "wx/wx.h"

class cMain : public wxFrame
{
public:
	cMain();
	~cMain();

public:
	int nFieldWidth = 10;
	int nFieldHeight = 10;
	wxButton** btn;
	int* nField = nullptr; //tells wether mine exists or not
	bool bFirstClick = true;

	
	wxMenuBar *menuBar = nullptr;
	wxMenu *menuGameSession = nullptr;
	wxMenu *menuLevel = nullptr;

	wxMenuItem *menuSave = nullptr;
	wxMenuItem *menuLoad = nullptr;
	wxMenuItem *menuRestart = nullptr;

	wxMenuItem *menuEasy = nullptr;
	wxMenuItem *menuNormal = nullptr;
	wxMenuItem *menuHard = nullptr;
	wxMenuItem *menuShowHelp = nullptr;

	void setMenuBar();

	void OnButtonClicked(wxCommandEvent& evt);

	void resetGame();

	void updateNeighbouringMinesNum(int x, int y);

	void populateMinefield(int mines, int x, int y);

	void OnButtonSaveClicked(wxCommandEvent& evt);

	void OnButtonMenuClicked(wxCommandEvent& evt);

	void OnButtonRestartClicked(wxCommandEvent& evt);

	void OnButtonEasyClicked(wxCommandEvent& evt);

	void OnButtonNormalClicked(wxCommandEvent& evt);

	void OnButtonHardClicked(wxCommandEvent& evt);

	void OnButtonShowHelpClicked(wxCommandEvent& evt);

	wxDECLARE_EVENT_TABLE();
};

