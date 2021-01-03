#pragma once

#include "wx/wx.h"
#include <wx/string.h>

class cMain : public wxFrame
{
public:
	cMain();
	~cMain();

public:
	int nFieldWidth = 10;
	int nFieldHeight = 10;
	int mines = 30;
	int hints;
	int clicked; // Counts clicked fields
	wxButton** btn;
	int* nField = nullptr; // Tells wether mine exists or not
	bool bFirstClick = true;

	wxMenuBar *menuBar = nullptr;
	wxMenu *menuGameSession = nullptr;
	wxMenu *menuLevel = nullptr;

	wxMenuItem *menuSave = nullptr;
	wxMenuItem *menuLoad = nullptr;
	wxMenuItem *menuRestart = nullptr;
	wxMenuItem *menuSolve = nullptr;

	wxMenuItem *menuEasy = nullptr;
	wxMenuItem *menuNormal = nullptr;
	wxMenuItem *menuHard = nullptr;
	wxMenuItem *menuShowHint = nullptr;
	wxMenuItem *menuShowHelp = nullptr;

	wxMenuItem* showHint = nullptr;

#ifdef _DEBUG
	wxMenuItem* menuDEBUG = nullptr;
#endif

	wxString labelBaseTitle;

	struct lastClickedField
	{
		int x;
		int y;
	} lastClicked;

#ifndef _DEBUG
	enum level { easy, normal, hard } lvl;
#endif // ! _DEBUG
#ifdef _DEBUG
	enum level { easy, normal, hard, debug } lvl;
	void  OnButtonDEBUGClicked(wxCommandEvent& evt);
#endif // 

	void setLevel(level);

	void setMenuBar();

	void OnButtonClicked(wxCommandEvent& evt);

	void resetGame();

	void updateNeighbouringMinesNum(int x, int y);

	void populateMinefield(int x, int y);

	void OnButtonSaveClicked(wxCommandEvent& evt);

	void OnButtonLoadClicked(wxCommandEvent& evt);

	void OnButtonRestartClicked(wxCommandEvent& evt);

	void OnButtonSolveClicked(wxCommandEvent& evt);

	void OnButtonEasyClicked(wxCommandEvent& evt);

	void OnButtonNormalClicked(wxCommandEvent& evt);

	void OnButtonHardClicked(wxCommandEvent& evt);

	void OnButtonShowHelpClicked(wxCommandEvent& evt);

	void OnButtonShowHintClicked(wxCommandEvent& evt);

	void basicSolver(wxCommandEvent& evt);

	std::string getOpenFilePath();

	std::string getSaveFilePath();
	
	wxDECLARE_EVENT_TABLE();
};

