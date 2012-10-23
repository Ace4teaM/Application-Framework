#ifndef MAIN_H
#define MAIN_H

#include <QtGui>
#include <QtCore>
#include <QMenu>
#include <QMenuBar>
#include <iostream>
#include <stdio.h>
#include "qt/command.h"
#include "qt/result.h"

using namespace afw;
using namespace qafw;

class CmdExit : public QWidget, public qafw::QCommand{
    Q_OBJECT
public:
    CmdExit();

    /* command execution dispatch */
    const QResult* exec(QObject* context);

    /* Quitte l'application principale */
    const QResult* quit(QApplication* context);
};

class CmdAbout : public QWidget, public qafw::QCommand{
    Q_OBJECT
public:
    CmdAbout();

    /* command execution dispatch */
    const QResult* exec(QObject* context);

    /* Affiche une boite de dialogue */
    const QResult* showDialog(QMainWindow* context);
};

class MainWindow : public QMainWindow
{
    Q_OBJECT
protected:
    QCommand* cmd1;
    CmdExit* cmd2;

public:
    MainWindow(QMainWindow* parent = 0, Qt::WFlags fl = Qt::Window);
    void addCommand(QMenu* menu, QCommand* cmd );
private slots:
    void execCmd(/*QObject* obj*/);
};

#endif // MAIN_H
